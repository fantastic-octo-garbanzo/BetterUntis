package com.sapuseven.untis.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sapuseven.untis.R
import com.sapuseven.untis.adapters.ErrorsAdapter
import com.sapuseven.untis.dialogs.ErrorReportingDialog
import kotlinx.android.synthetic.main.activity_errors.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.io.File

class ErrorsActivity : BaseActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.activity_errors)

		loadErrorList()
	}

	private fun loadErrorList() {
		recyclerview_errors.layoutManager = LinearLayoutManager(this)
		recyclerview_errors.adapter = ErrorsAdapter(File(filesDir, "crash").listFiles()?.sortedDescending()?.map {
			ErrorData(
					readCrashData(it),
					DateTime(it.name.replace(".log", "").toLong()).toString(DateTimeFormat.mediumDateTime())
			)
		} ?: emptyList())
		(recyclerview_errors.adapter as ErrorsAdapter).setOnItemClickListener { item ->
			ErrorReportingDialog(this).showGenericErrorDialog(item.log)
		}
	}

	data class ErrorData(
			val log: String,
			val time: String
	)
}
