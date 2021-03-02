package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var theSlider: Slider
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = text_view
        theSlider = slider
        editText = edit_text
        editText.addTextChangedListener(
                object : TextWatcher {

                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        // Fires right as the text is being changed (even supplies the range of text)
                    }

                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                        // Fires right before text is changing
                    }

                    override fun afterTextChanged(s: Editable) {
                        // Fires right after the text has changed
                        if (s.isEmpty()) {
                            textView.setText("")
                            textView.setVisibility(View.INVISIBLE)
                        } else {
                            textView.setVisibility(View.VISIBLE)
                            val v = editText.text.toString().toBigDecimal()
                            updateTextView(v)
                        }
                    }
                })

        theSlider.addOnChangeListener { slider, value, fromUser ->
            if (editText.text.toString().isEmpty()) {
                textView.setText("")
                textView.setVisibility(View.INVISIBLE)
            } else {
                textView.setVisibility(View.VISIBLE)
                val v = editText.text.toString().toBigDecimal()
                updateTextView(v)
            }
        }
    }

    fun updateTextView(v: BigDecimal) {
        val p = theSlider.value
        // val P = v * p / 100.0
        val content = "Bill value: ${"%.0f".format(v)}, tip percentage: ${"%.0f%%".format(p)}"
        textView.setText(content)
    }
}
