package com.leonardo.jokenpo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //Linhas iniciais padrão
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Em breve:

        - Repassar tudo para duas funções: Escolha do Computador e Escolha do usuário
        e utilizar o onCreate apenas para chamar as funções e obter os resultados;

        - Criar um SQLite para salvar o histórico de jogos.

        */

        //Opções do usuário
        val btnPedra = findViewById<Button>(R.id.btnPedra)
        val btnPapel = findViewById<Button>(R.id.btnPapel)
        val btnTesoura = findViewById<Button>(R.id.btnTesoura)

        //Escolha do usuário
        val options = listOf(R.drawable.pedra, R.drawable.papel, R.drawable.tesoura)

        val imgUsuario = findViewById<ImageView>(R.id.imgUsuario)

        //Resultado final
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        //Opção Pedra
        btnPedra.setOnClickListener {
            txtResultado.text = jokenpo(btnPedra.id, options)
            imgUsuario.setImageResource(R.drawable.pedra)
        }

        //Opção Papel
        btnPapel.setOnClickListener {
            txtResultado.text = jokenpo(btnPapel.id, options)
            imgUsuario.setImageResource(R.drawable.papel)
        }

        //Opção Tesoura
        btnTesoura.setOnClickListener {
            txtResultado.text = jokenpo(btnTesoura.id, options)
            imgUsuario.setImageResource(R.drawable.tesoura)
        }
    }


    private fun jokenpo(userChoice: Int, options: List<Int>): String {

        //Escolha do computador
        val imgComputador = findViewById<ImageView>(R.id.imgComputador)

        //Escolha aleatória do computador
        val computerChoice = options.random()
        imgComputador.setImageResource(computerChoice)

        //Resultado final
        return when (userChoice) {
            R.id.btnPedra ->
                when (computerChoice) {
                    R.drawable.pedra -> "Empate!"
                    R.drawable.papel -> "Você perdeu!"
                    R.drawable.tesoura -> "Você ganhou!"
                    else -> "Erro"
                }

            R.id.btnPapel ->
                when (computerChoice) {
                    R.drawable.pedra -> "Você ganhou!"
                    R.drawable.papel -> "Empate!"
                    R.drawable.tesoura -> "Você perdeu!"
                    else -> "Erro"
                }

            R.id.btnTesoura ->
                when (computerChoice) {
                    R.drawable.pedra -> "Você perdeu!"
                    R.drawable.papel -> "Você ganhou!"
                    R.drawable.tesoura -> "Empate!"
                    else -> "Erro"
                    }

            else -> "Erro"
        }
    }
}