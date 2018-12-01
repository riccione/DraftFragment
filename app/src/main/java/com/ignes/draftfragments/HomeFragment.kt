package com.ignes.draftfragments


import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A main fragment
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigation by action
        /* Actions allow you to attach NavOptions in the navigation XML file, rather than
        specifying them programmatically.
        **Benefits:
        You can visualize the navigation paths through your app
        Actions can contain additional associated attributes you can set, such as a transition animation, arguments values, and backstack behavior
        You can use the plugin safe args to navigate, which you'll see shortly
        */
        /*
        view.findViewById<Button>(R.id.navigate_action_button)?.setOnClickListener {
            //Navigation.createNavigateOnClickListener(R.id.next_action, null)
            val action = HomeFragmentDirections.nextAction()
            action.setFlowStepNumber(1)
            findNavController().navigate(action)
        }
        */

        view.findViewById<Button>(R.id.easy_btn)?.setOnClickListener {
            val mode = 1
            val action = HomeFragmentDirections.actionHomeDestToPlayDest()
            action.setMode(mode)
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.stats_btn)?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeDestToStatsDest()
            findNavController().navigate(action)
        }
    }
}