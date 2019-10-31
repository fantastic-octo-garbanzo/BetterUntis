package com.sapuseven.untis.data.databases

import android.provider.BaseColumns


object UserDatabaseContract {
	object Users : BaseColumns {
		const val TABLE_NAME = "users"
		const val COLUMN_NAME_APIURL = "apiUrl"
		const val COLUMN_NAME_SCHOOL_ID = "schoolId"
		const val COLUMN_NAME_USER = "user"
		const val COLUMN_NAME_KEY = "auth"
		const val COLUMN_NAME_ANONYMOUS = "anonymous"
		const val COLUMN_NAME_TIMEGRID = "timeGrid"
		const val COLUMN_NAME_USERDATA = "userData"
		const val COLUMN_NAME_MASTERDATATIMESTAMP = "masterDataTimestamp"
		const val COLUMN_NAME_SETTINGS = "settings"
		const val COLUMN_NAME_CREATED = "time_created"

		const val SQL_CREATE_ENTRIES_V2 =
				"CREATE TABLE $TABLE_NAME (" +
						"${BaseColumns._ID} INTEGER PRIMARY KEY," +
						"$COLUMN_NAME_APIURL VARCHAR(128)," +
						"$COLUMN_NAME_SCHOOL_ID INT(12)," +
						"$COLUMN_NAME_USER VARCHAR(64)," +
						"$COLUMN_NAME_KEY VARCHAR(16)," +
						"$COLUMN_NAME_ANONYMOUS INT(1) NOT NULL," +
						"$COLUMN_NAME_TIMEGRID VARCHAR(65535) NOT NULL," +
						"$COLUMN_NAME_MASTERDATATIMESTAMP LONG NOT NULL," +
						"$COLUMN_NAME_USERDATA VARCHAR(65535) NOT NULL," +
						"$COLUMN_NAME_SETTINGS VARCHAR(65535) NOT NULL," +
						"$COLUMN_NAME_CREATED DATETIME DEFAULT CURRENT_TIMESTAMP)"

		const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
	}
}
