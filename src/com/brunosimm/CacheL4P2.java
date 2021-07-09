package com.brunosimm;

import com.brunosimm.Memoria;

public class CacheL4P2 {

    private int nroBitsTag;
    private int nroBitsLinhas;
    private int nroBitsPalavras;

    private int nroLinhas;
    private int nroPalavras;
    private Memoria memoria;


    private String[][] memoriaCache;
    private int hits;
    private int miss;

    //Recebendo memoria de mais baixo nível por injeção de dependencia.
    public CacheL4P2(int nroBitLinhas, int nroBitsPalavras, int nroBitsTag, Memoria memoria) {
        this.nroBitsLinhas = nroBitLinhas;
        this.nroBitsPalavras = nroBitsPalavras;
        this.nroBitsTag = nroBitsTag;

        this.nroLinhas = (int) Math.pow(2,nroBitLinhas);
        this.nroPalavras = (int) Math.pow(2,nroBitsPalavras);

        this.memoria = memoria; //Memoria principal
        this.memoriaCache = new String[nroLinhas][nroPalavras+2]; //[linhas] [nroPalavras (+2 = linha+tag)]
        createLinhas(); //Alimentar primeira coluna da cache com as linhas.
    }

    public void createLinhas(){
        int count = 0;
        for (String[] linhas: memoriaCache) {
            //Alimentando linhas de acordo com o nro de linhas da cache (16).
            linhas[0] = String.format("%"+nroBitsTag+"s", Integer.toBinaryString(count)).replace(' ', '0');
            count++;
        }
    }

    public void show(){
        System.out.print("\n Linha     Tag       ...Dados");
        for (String[] linhas: memoriaCache) { //retorna um vetor por interação (linha)
            System.out.println("\n");
            for (String coluna : linhas) {
                System.out.print(" "+coluna);
            }
        }
    }

    public boolean search(String endereco){ //000000000 100 111 0
        String tag = endereco.substring(0,nroBitsTag);
        String linha = endereco.substring(nroBitsTag,nroBitsTag+ (nroBitsLinhas));
        String dado = endereco.substring(nroBitsTag + (nroBitsLinhas),endereco.length() - 1); //excluindo o byte

        for (int i = 0; i < memoriaCache.length; i++) { //000000000 100 111 0

            String linhacache = memoriaCache[i][0].substring(5);
            String tagcache = memoriaCache[i][1];

            if ((memoriaCache[i][2] != null) && tagcache != null && (linhacache.equals(linha)) && (tagcache.equals(tag))){ //hit
                this.hits++;
                System.out.println("L4P2 - Endereço: "+endereco+ " -> HIT");
                return true;
            }

        }
        //miss -> não encontrou na cache no for anterior. Setar novos dados e tag
        this.miss++;
        System.out.println("L4P2 - Endereço: "+endereco+ " -> MISS");
        for (int i = 0; i < memoriaCache.length; i++) { //000000000 1001 11 0

            String linhacache = memoriaCache[i][0].substring(5);
            String tagcache = memoriaCache[i][1];

            if (tagcache == null){
                tagcache = "";
            }

            if ((linhacache.equals(linha))){
                memoriaCache[i][1] = tag;
                String[] bloco = memoria.getBloco4ByEndereco(endereco);
                for (int j = 2; j < nroPalavras+2; j++) {
                    memoriaCache[i][j] = bloco[j-2];
                }
                return false;
            }

        }
        return false;
    }

    public int getHits() {
        return hits;
    }

    public int getMiss() {
        return miss;
    }

    public double getPercentualAcertos(){
        return (double) this.hits / (this.hits + this.miss) * 100;
    }
}
