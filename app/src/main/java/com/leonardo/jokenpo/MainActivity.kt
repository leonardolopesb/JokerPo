package com.leonardo.jokenpo

import android.media.Image
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
        val txtUserChoice = findViewById<TextView>(R.id.txtUserChoice)

        //Resultado final
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        //Opção Pedra
        btnPedra.setOnClickListener {
            txtUserChoice.text = "Sua escolha: Pedra"
            txtResultado.text = jokenpo(btnPedra.id, options)
        }

        //Opção Papel
        btnPapel.setOnClickListener {
            txtUserChoice.text = "Sua escolha: Papel"
            txtResultado.text = jokenpo(btnPapel.id, options)
        }

        //Opção Tesoura
        btnTesoura.setOnClickListener {
            txtUserChoice.text = "Sua escolha: Tesoura"
            txtResultado.text = jokenpo(btnTesoura.id, options)
        }
    }


    private fun jokenpo(userChoice: Int, options: List<Int>): String {

        //Escolha do computador
        val imgComputador = findViewById<ImageView>(R.id.imgComputador)

        //Escolha aleatória do computador
        val computerChoice = options.random()
        val txtComputerChoice: TextView = findViewById(R.id.txtComputerChoice)

        imgComputador.setImageResource(computerChoice)

        //Texto da escolha do computador
        val computerChoiceTxt = when (computerChoice) {
            R.drawable.pedra -> "Pedra"
            R.drawable.papel -> "Papel"
            R.drawable.tesoura -> "Tesoura"
            else -> "Erro"
        }
        txtComputerChoice.text = "Minha escolha: $computerChoiceTxt"

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