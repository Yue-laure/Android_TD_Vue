package fr.gobelins.dmi1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ComputeActivity : AppCompatActivity() {
    private lateinit var btnCompute: Button
    private  lateinit var PremirOperandIn: TextInputEditText
    private  lateinit var DeuxiemeOperandIn: TextInputEditText
    private  lateinit var ResultTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)

        val operation = intent.getStringExtra("operation") ?: "ADD"

        btnCompute = findViewById(R.id.btn_compute)
        PremirOperandIn = findViewById(R.id.first_operand)
        DeuxiemeOperandIn= findViewById(R.id.second_operand)
        ResultTextView   = findViewById(R.id.result_text_view)

        btnCompute.setOnClickListener {

            val operand1 = PremirOperandIn.text.toString().toDouble()
            val operand2 = DeuxiemeOperandIn.text.toString().toDouble()

            val result = when(operation){
                "ADD"->operand1+operand2
//                "SUBS"->operand1-operand2
//                "MULT"->operand1*operand2
//                "DIV"->operand1/operand2
                else -> throw IllegalArgumentException("Invalid operation: $operation")
            }
            ResultTextView.text=getString(R.string.result_format,result.toString())
        }



    }
}