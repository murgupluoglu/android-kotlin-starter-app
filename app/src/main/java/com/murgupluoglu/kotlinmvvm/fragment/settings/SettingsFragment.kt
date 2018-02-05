package com.murgupluoglu.kotlinmvvm.fragment.settings

import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.fragment.BaseFragment
import me.yokeyword.fragmentation.ISupportFragment

/**
 * Created by mustafa.urgupluoglu on 2/5/18.
 */

class SettingsFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.fragment_settings

    companion object {

        fun newInstance(): ISupportFragment {

            /*
            Bundle args = new Bundle();
            args.putString(Constants.PARAM_SCREEN, screen.toString());
            fragment.setArguments(args);
            */

            return SettingsFragment()
        }
    }
}