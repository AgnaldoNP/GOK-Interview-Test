package dev.agnaldo.gokinterviewtest.ui.base

import androidx.lifecycle.ViewModel
import com.rugovit.eventlivedata.EventLiveData
import com.rugovit.eventlivedata.MutableEventLiveData

abstract class BaseViewModel : ViewModel() {

    private val events = MutableEventLiveData<Event>()
    val observableEvents: EventLiveData<Event> = events

    interface Event

}
