package com.nguyendinhdoan.editnamedialogfragmentwithkotlin

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_edit_name_dialog.*
import java.lang.ClassCastException

class EditNameDialogFragment : DialogFragment(), TextView.OnEditorActionListener, View.OnClickListener {

    private lateinit var listener: EditNameDialogListener

    companion object {

        private const val TITLE_KEY = "TITLE_KEY"

        fun newInstance(dialogTitle: String): EditNameDialogFragment {
            val editNameDialogFragment = EditNameDialogFragment()
            val args = Bundle()
            args.putString(TITLE_KEY, dialogTitle)
            editNameDialogFragment.arguments = args
            return editNameDialogFragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is EditNameDialogListener) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement EditNameDialogListener ")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_name_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        addEvents()
    }

    private fun setupUI() {
        // get dialog title form bundle and set for dialog
        val dialogTitle = arguments?.getString(TITLE_KEY)
        dialog.setTitle(dialogTitle)
        isCancelable = false

        // set request focus the edit text and show soft keyboard automatically
        your_name_edit_text.requestFocus()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private fun addEvents() {
        your_name_edit_text.setOnEditorActionListener(this)
        submit_name_button.setOnClickListener(this)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            displayYourName()
            return true
        }
        return false
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.submit_name_button) {
            displayYourName()
        }
    }

    private fun displayYourName() {
        val yourName = your_name_edit_text.text.toString()
        listener.onFinishEditNameListener(yourName)

        // close the dialog and back to the parent activity
        dismiss()
    }

    interface EditNameDialogListener {
        fun onFinishEditNameListener(yourName: String)
    }
}