package edu.itesm.tiendaperroingles.view

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.itesm.tiendaperroingles.R
import edu.itesm.tiendaperroingles.databinding.FragmentCalendarBinding
import edu.itesm.tiendaperroingles.view.dialog.DatePickerFragment
import edu.itesm.tiendaperroingles.view.dialog.TimePickerFragment
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private var year: Int = 0
    private var month: Int = 0
    private var dayOfMonth: Int = 0
    private var hour: Int = 0
    private var minutes: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.datePicker.setOnClickListener{ showDatePicker() }

        binding.timePicker.setOnClickListener { showTimePicker() }

        binding.addEvent.setOnClickListener {
            val startMillis: Long = Calendar.getInstance().run {
                set(year, month, dayOfMonth, hour, minutes)
                timeInMillis
            }
            val endMillis: Long = Calendar.getInstance().run {
                set(year, month, dayOfMonth, hour, minutes)
                timeInMillis
            }
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
                .putExtra(CalendarContract.Events.TITLE, "Cita con veterinario")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, "veterinario@perroingles.com")
            startActivity(intent)
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerFragment{day, month, year ->
            dayOfMonth = day
            this.year = year
            this.month = month

            binding.datePicker.setText("$day/$month/$year")
        }

        return datePicker.show(this.childFragmentManager, "datePicker")
    }

    private fun showTimePicker() {
        val timePicker = TimePickerFragment{hour, minutes ->
            this.hour = hour
            this.minutes = minutes

            binding.timePicker.setText("$hour:$minutes")
        }

        return timePicker.show(this.childFragmentManager, "datePicker")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}