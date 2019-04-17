package com.nguyendinhdoan.editnamedialogfragmentwithkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), EditNameDialogFragment.EditNameDialogListener {

    companion object {
        private const val EDIT_NAME_DIALOG_TAG = "EDIT_NAME_DIALOG_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show_dialog_button.setOnClickListener {
            showEditNameDialog()
        }
    }

    private fun showEditNameDialog() {
        val dialogTitle = "Edit Name"
        val editNameDialogFragment = EditNameDialogFragment.newInstance(dialogTitle)
        editNameDialogFragment.show(supportFragmentManager, EDIT_NAME_DIALOG_TAG)
    }

    override fun onFinishEditNameListener(yourName: String) {
        Toast.makeText(this@MainActivity, "name: $yourName", Toast.LENGTH_SHORT).show()
    }
}
