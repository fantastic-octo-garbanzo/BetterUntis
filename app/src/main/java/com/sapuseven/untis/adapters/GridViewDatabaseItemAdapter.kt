package com.sapuseven.untis.adapters

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import com.sapuseven.untis.helpers.timetable.TimetableDatabaseInterface
import com.sapuseven.untis.models.untis.timetable.PeriodElement
import java.util.*

open class GridViewDatabaseItemAdapter(context: Context)
	: ArrayAdapter<String>(context, android.R.layout.simple_list_item_1), Filterable {
	private var originalItems: List<PeriodElement> = emptyList()
	private val filteredItems: MutableList<PeriodElement> = originalItems.toMutableList()
	private val filter = ItemFilter()
	var timetableDatabaseInterface: TimetableDatabaseInterface? = null
	var type: TimetableDatabaseInterface.Type? = null
	set(value) {
		field = value
		originalItems = timetableDatabaseInterface?.getElements(type) ?: emptyList()
		filteredItems.clear()
		filteredItems.addAll(originalItems)
	}

	override fun getFilter(): Filter {
		return filter
	}

	override fun getCount(): Int {
		return filteredItems.size
	}

	override fun getItem(position: Int): String {
		return timetableDatabaseInterface?.getShortName(filteredItems[position].id, type) ?: ""
	}

	fun itemAt(position: Int): PeriodElement {
		return filteredItems[position]
	}

	private inner class ItemFilter : Filter() {
		override fun publishResults(constraint: CharSequence, results: FilterResults) {
			filteredItems.clear()

			if (results.values is List<*>)
				filteredItems.addAll((results.values as List<*>).map { it as PeriodElement })
			else
				filteredItems.addAll(originalItems)

			notifyDataSetChanged()
		}

		override fun performFiltering(constraint: CharSequence?): FilterResults {
			val results = FilterResults()
			val filteredList = ArrayList<PeriodElement>()

			if (constraint.isNullOrBlank()) {
				results.count = originalItems.size
				results.values = originalItems
			} else {
				for (i in originalItems.indices) {
					val data = originalItems[i]
					if (timetableDatabaseInterface?.elementContains(data, constraint.toString().toLowerCase(Locale.getDefault())) == true)
						filteredList.add(data)

				}
				results.count = filteredList.size
				results.values = filteredList
			}
			return results
		}
	}
}