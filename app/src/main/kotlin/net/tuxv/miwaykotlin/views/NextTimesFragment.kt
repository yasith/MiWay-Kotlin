package net.tuxv.miwaykotlin.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.bindView
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.models.Time
import net.tuxv.miwaykotlin.presenters.NextTimesPresenter
import java.util.ArrayList
import kotlin.properties.Delegates

public class NextTimesFragment : Fragment() {
    val TAG = "NextTimesFragment"

    val stopName : TextView by bindView(R.id.stopName)

    val loading : View by bindView(R.id.loading)

    val timesGroup : ViewGroup by bindView(R.id.times)

    val time1 : TextView by bindView(R.id.time1)
    val time2 : TextView by bindView(R.id.time2)
    val time3 : TextView by bindView(R.id.time3)

    val emptyMessage : TextView by bindView(R.id.emptyMessage)

    var route : Route by Delegates.notNull()
    var stop : Stop by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater?.inflate(R.layout.fragment_next_times, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity = getActivity() as TimesActivity

        route = activity.route
        stop = activity.stop

        // TODO: Make sure this survives config changes
        NextTimesPresenter(route, stop).attachView(this)
    }

    override fun onStart() {
        super.onStart()

        stopName.setText(stop.name)
    }

    fun onContentLoaded(times : ArrayList<Time>) {
        loading.setVisibility(View.GONE)
        if(times.size() == 0) {
            timesGroup.setVisibility(View.GONE)
            emptyMessage.setVisibility(View.VISIBLE)
        } else {
            timesGroup.setVisibility(View.VISIBLE)
            emptyMessage.setVisibility(View.GONE)
            time1.setText(times.get(0)?.toString())
            time2.setText(times.get(1)?.toString())
            time3.setText(times.get(2)?.toString())
        }
    }

    // TODO: Implementation
    fun onError(error : Throwable, pullToRefresh : Boolean) {}
}




