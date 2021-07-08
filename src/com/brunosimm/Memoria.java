package com.brunosimm;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
    private List<String> dadosBin;

    public Memoria(String[] dadoshex) {
        this.dadosBin = new ArrayList<>();
        hexToBin(dadoshex);
    }

    private void hexToBin(String [] dadoshex){
        for (String dado : dadoshex) {
            dado = dado.toUpperCase();
            dado = dado.replaceAll("0", "0000");
            dado = dado.replaceAll("1", "0001");
            dado = dado.replaceAll("2", "0010");
            dado = dado.replaceAll("3", "0011");
            dado = dado.replaceAll("4", "0100");
            dado = dado.replaceAll("5", "0101");
            dado = dado.replaceAll("6", "0110");
            dado = dado.replaceAll("7", "0111");
            dado = dado.replaceAll("8", "1000");
            dado = dado.replaceAll("9", "1001");
            dado = dado.replaceAll("A", "1010");
            dado = dado.replaceAll("B", "1011");
            dado = dado.replaceAll("C", "1100");
            dado = dado.replaceAll("D", "1101");
            dado = dado.replaceAll("E", "1110");
            dado = dado.replaceAll("F", "1111");
            this.dadosBin.add(dado);
        }
    }

    public void show(){
        for (String dado : dadosBin) {
            System.out.println(dado);
        }
    }

    public String[] getBloco8ByEndereco(String endereco){
        String[] bloco = new String[8];
        int posicao = -1;

            for (int i = 0; i < dadosBin.size(); i++) {
                if (dadosBin.get(i).equals(endereco)){
                    posicao = i;
                    break;
                }
            }
            if (posicao == -1) { return bloco; } //vazio

            int count = 0;
            if (posicao > 3 && posicao < 193){
                for (int i = 3; i >= 0; i--) {
                    bloco[i] = dadosBin.get(posicao - (1+count));
                    count++;
                }
                count = 0;
                for (int i = 4; i < 8 ; i++) {
                    bloco[i] = dadosBin.get(posicao + (1+count));
                    count++;
                }
            } else if (posicao < 3) {
                count = 0;
                if (posicao == 0){
                    for (int i = 0; i < 8 ; i++) {
                        bloco[i] = dadosBin.get(i);
                    }
                } else if (posicao == 1){
                    bloco[0] = dadosBin.get(0);
                    for (int i = 1; i < 8 ; i++) {
                        bloco[i] = dadosBin.get(i);
                    }
                } else if (posicao == 2) {
                    bloco[0] = dadosBin.get(0);
                    bloco[1] = dadosBin.get(1);
                    for (int i = 2; i < 8 ; i++) {
                        bloco[i] = dadosBin.get(i);
                    }
                }
            }
        return bloco;
    }

    public List<String> getDadosBin() {
        return dadosBin;
    }

    public String[] getBloco4ByEndereco(String endereco) {
        String[] bloco = new String[4];
        int posicao = -1;

        for (int i = 0; i < dadosBin.size(); i++) {
            if (dadosBin.get(i).equals(endereco)){
                posicao = i;
                break;
            }
        }
        if (posicao == -1) {
            return bloco;
        } //vazio

            int count = 0;
            bloco[0] = dadosBin.get(posicao);
            if (posicao < 193){
                for (int i = 1; i <= 3 ; i++) {
                    bloco[i] = dadosBin.get(posicao + (1+count));
                    count++;
                }
            } else { // >=193
                bloco[1] = dadosBin.get(194);
                bloco[2] = dadosBin.get(195);
                bloco[3] = dadosBin.get(196);
            }
        return bloco;
    }
}
