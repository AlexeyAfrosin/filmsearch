package com.afrosin.filmsearch.view.googlemaps

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.afrosin.filmsearch.BuildConfig
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.databinding.FragmentMapsBinding
import com.afrosin.filmsearch.model.Person
import com.afrosin.filmsearch.utils.hide
import com.afrosin.filmsearch.utils.show
import com.afrosin.filmsearch.utils.showSnackBar
import com.afrosin.filmsearch.viewmodel.AppState
import com.afrosin.filmsearch.viewmodel.PersonDetailsViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.io.IOException

class MapsFragment : Fragment() {
    private val LOCATION_REQUEST_CODE = 11

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

    private lateinit var map: GoogleMap
    private val markers: ArrayList<Marker> = arrayListOf()

    private val personDetailsViewModel: PersonDetailsViewModel by lazy {
        ViewModelProvider(this).get(PersonDetailsViewModel::class.java)
    }

    companion object {
        const val PERSON_DATA = "PERSON_DATA"

        fun newInstance(bundle: Bundle? = null): MapsFragment {
            val fragment = MapsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->

        map = googleMap
        val initialPlace = LatLng(52.52000659999999, 13.404953999999975)
        googleMap.addMarker(
            MarkerOptions().position(initialPlace).title(getString(R.string.marker_start))
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(initialPlace))
        googleMap.setOnMapLongClickListener { latLng ->
            getAddressAsync(latLng)
            addMarkerToArray(latLng)
            drawLine()
        }
        activateMyLocation(googleMap)

    }

    private fun drawLine() {
        val last: Int = markers.size - 1
        if (last >= 1) {
            val previous: LatLng = markers[last - 1].position
            val current: LatLng = markers[last].position
            map.addPolyline(
                PolylineOptions()
                    .add(previous, current)
                    .color(Color.RED)
                    .width(5f)
            )
        }
    }

    private fun addMarkerToArray(location: LatLng) {
        val marker =
            setMarker(location, markers.size.toString(), R.drawable.ic_map_pin)
        markers.add(marker)
    }

    private fun setMarker(location: LatLng, title: String, resourceId: Int): Marker {
        return map.addMarker(
            MarkerOptions()
                .position(location)
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(resourceId))
        )

    }

    private fun getAddressAsync(location: LatLng) {
        context?.let {
            val geoCoder = Geocoder(it)
            Thread {
                try {
                    val address = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                    binding.textAddress.post {
                        binding.textAddress.text = address[0].getAddressLine(0)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val person: Person? = arguments?.getParcelable<Person>(PERSON_DATA)
        if (person != null) {
            loadPersonData(person)
        } else {
            getMap()
        }
    }

    private fun loadPersonData(person: Person, langDataSet: String = "ru-RU") {
        personDetailsViewModel.getLiveData().observe(viewLifecycleOwner, { goToPlaceOfBirth(it) })
        personDetailsViewModel.getDataFromFromServer(
            person.id,
            BuildConfig.FILM_API_KEY,
            langDataSet
        )
    }

    private fun goToPlaceOfBirth(appState: AppState?) {
        when (appState) {
            is AppState.SuccessPersonDetails -> {
                val personData = appState.personDetails
                binding.includedLoadingLayout.loadingLayout.hide()
                binding.searchAddress.setText(personData.placeOfBirth)
                getMap()
            }
            is AppState.Loading -> {
                binding.includedLoadingLayout.loadingLayout.show()
            }
            is AppState.Error -> {
                binding.includedLoadingLayout.loadingLayout.hide()
                binding.mapsFragmentContainer.showSnackBar(getString(R.string.error_text),
                    getString(R.string.reload_text), { getMap() })
            }
        }
    }

    private fun getMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        initSearchByAddress()
        if (binding.searchAddress.text != null) {
            binding.buttonSearch.callOnClick()
        }
    }

    private fun initSearchByAddress() {
        binding.buttonSearch.setOnClickListener {
            val geoCoder = Geocoder(it.context)
            val searchText = binding.searchAddress.text.toString()
            Thread {
                try {
                    val addresses = geoCoder.getFromLocationName(searchText, 1)
                    if (addresses.size > 0) {
                        goToAddress(addresses, it, searchText)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }


    }

    private fun goToAddress(addresses: MutableList<Address>, view: View, searchText: String) {
        val location = LatLng(addresses[0].latitude, addresses[0].longitude)
        view.post {
            setMarker(location, searchText, R.drawable.ic_map_marker)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }

    private fun activateMyLocation(googleMap: GoogleMap) {
        context?.let {
            val isPermissionGradient = ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            if (isPermissionGradient) {
                googleMap.isMyLocationEnabled = isPermissionGradient
                googleMap.uiSettings.isMyLocationButtonEnabled = isPermissionGradient
            } else {
                requestPermissions()
                activateMyLocation(googleMap)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun requestPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_REQUEST_CODE
        )
    }
}