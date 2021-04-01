package com.afrosin.filmsearch.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.databinding.FragmentContactsBinding

const val REQUEST_CODE = 1

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkContactPermission()
    }

    private fun checkContactPermission() {
        context?.let {
            when {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    getContacts()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    AlertDialog.Builder(it)
                        .setTitle(getString(R.string.contact_access_tittle))
                        .setMessage(getString(R.string.contact_access_message))
                        .setPositiveButton(getString(R.string.contact_access_positive_btn_text)) { _, _ ->
                            requestPermission()
                        }
                        .setNegativeButton(getString(R.string.contact_access_negative_btn_text)) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
                else -> {
                    requestPermission()
                }
            }
        }
    }

    @SuppressLint("Recycle")
    private fun getContacts() {
        context?.let {
            val contentRecolver: ContentResolver = it.contentResolver
            val cursorWithContacts: Cursor? = contentRecolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

            cursorWithContacts?.let { cursor ->
                while (cursorWithContacts.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    addView(it, name)
                }

                cursorWithContacts?.close()
            }
        }
    }

    private fun addView(context: Context, textToShow: String) {
        binding.containerForContacts.addView(AppCompatTextView(context).apply {
            text = textToShow
            textSize = resources.getDimension(R.dimen.contact_text_size)
        })
    }

    private fun requestPermission() {
        requestPermissions(arrayOf((Manifest.permission.READ_CONTACTS)), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts()
                } else {
                    context?.let {
                        AlertDialog.Builder(it)
                            .setTitle(getString(R.string.contact_access_tittle))
                            .setMessage(getString(R.string.contact_access_message))
                            .setNegativeButton(getString(R.string.contact_access_negative_btn_close_text)) { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                    }
                }
                return
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }

    companion object {

        @JvmStatic
        fun newInstance() = ContactsFragment()
    }
}