package example.com.searchview.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import example.com.searchview.R
import example.com.searchview.model.AndroidVersion
import kotlinx.android.synthetic.main.card_row.view.*


class DataAdapter(private val list: List<AndroidVersion>,
                  private val context: Context) : RecyclerView.Adapter<DataAdapter.ViewHolder>(),
        Filterable {

    var filteredList: List<AndroidVersion> = list

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val androidVersion = filteredList[position]

        holder.bindView(androidVersion)

    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()

                filteredList = when {
                    charString.isEmpty() -> list
                    else -> {
                        val internalFilteredList: MutableList<AndroidVersion> = mutableListOf()
                        for (androidVersion in list) {
                            if (androidVersion.api.toLowerCase().contains(charString)
                                    || androidVersion.name.toLowerCase().contains(charString)
                                    || androidVersion.ver.toLowerCase().contains(charString)) {
                                internalFilteredList.add(androidVersion)
                            }
                        }
                        internalFilteredList
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredList = p1?.values as List<AndroidVersion>
                notifyDataSetChanged()
            }

        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(androidVersion: AndroidVersion) {
            val name = itemView.tvName
            val version = itemView.tvVersion
            val apiLevel = itemView.tvApiLevel

            name.text = androidVersion.name
            version.text = androidVersion.ver
            apiLevel.text = androidVersion.api
        }


    }
}