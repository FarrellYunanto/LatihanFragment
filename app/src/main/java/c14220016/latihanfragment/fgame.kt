package c14220016.latihanfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.motion.widget.Debug
import java.sql.Array

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fgame.newInstance] factory method to
 * create an instance of this fragment.
 */
class fgame : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fgame, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Variable buat game
        var prevalue = ""
        var tries = 0
        var numbers = mutableListOf<Int>()
        if (arguments != null){
            val txtHasil = arguments?.getString("CONFIG")?.toInt()
            val sampai = txtHasil?.plus(5)
            if (txtHasil != null) {
                for (i in txtHasil until sampai!!){
                    numbers.add(i)
                    numbers.add(i)
                }
            }

        } else {
            numbers = arrayOf(1,1,2,2,3,3,4,4,5,5).toMutableList()
        }

        val score = view.findViewById<TextView>(R.id.gameScore)
        val buttons = arrayOf(
            view.findViewById<Button>(R.id.guessB1),
            view.findViewById<Button>(R.id.guessB2),
            view.findViewById<Button>(R.id.guessB3),
            view.findViewById<Button>(R.id.guessB4),
            view.findViewById<Button>(R.id.guessB5),
            view.findViewById<Button>(R.id.guessB6),
            view.findViewById<Button>(R.id.guessB7),
            view.findViewById<Button>(R.id.guessB8),
            view.findViewById<Button>(R.id.guessB9),
            view.findViewById<Button>(R.id.guessB10),
        )

//        Variable buat button to score
        val mFragmentManager = parentFragmentManager
        mFragmentManager.findFragmentByTag(fscore::class.java.simpleName)
        val mBundle = Bundle()

//        Nambahin onclick ke tiap button
        for (button in buttons){
            button.setOnClickListener {
                button.text = numbers.removeAt(numbers.indices.random()).toString()

                if (prevalue != ""){
                    if (prevalue == button.text.toString()){
                        score.text = (score.text.toString().toInt() + 10).toString()
                        prevalue = ""
                    } else {
                        score.text = (score.text.toString().toInt() - 5).toString()
                        prevalue = ""
                    }
                } else {
                    prevalue = button.text.toString()
                }

                button.isEnabled = false

                tries += 1

                if (tries == 10){
                    val mfScore = fscore()
                    mBundle.putString("SCORE", score.text.toString())
                    mfScore.arguments = mBundle
                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.mainframe, mfScore, fscore::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }
                }
            }
        }

//        button give up
        val _buttonToScore = view.findViewById<Button>(R.id.btnGiveUp)
        _buttonToScore.setOnClickListener {
            val mfScore = fscore()
            mBundle.putString("SCORE", score.text.toString())
            mfScore.arguments = mBundle
            mFragmentManager.beginTransaction().apply {
                replace(R.id.mainframe, mfScore, fscore::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fgame.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fgame().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}