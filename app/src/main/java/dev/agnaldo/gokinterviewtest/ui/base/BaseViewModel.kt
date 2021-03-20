package dev.agnaldo.gokinterviewtest.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rugovit.eventlivedata.EventLiveData
import com.rugovit.eventlivedata.MutableEventLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val events = MutableEventLiveData<Event>()
    val observableEvents: EventLiveData<Event> = events

    interface Event

    fun postEventToView(event: Event) {
        viewModelScope.launch(Dispatchers.Main) {
            events.value = event
        }
    }

}
