package com.brunosimm;

import java.util.List;
import com.brunosimm.Memoria;
import com.brunosimm.CacheL3P3;
import com.brunosimm.CacheL4P2;

public class Main {

    public static void main(String[] args) {

        String[] dados = {
                "0000", "0002", "0004", "0006", "0008", "000a", "000c", "000e", "0010", "0030", "0032", "7ffc",
                "0034", "0036", "7ffa", "0038", "003a", "003c", "0056", "003e", "0040", "0042", "0044", "0046", "0048",
                "004a", "003a", "003c", "0057", "003e", "0040", "0042", "0044", "0046", "0048", "004a", "003a", "003c",
                "0058", "003e", "0040", "0042", "0044", "0046", "0048", "004a", "004c", "7ffa", "004e", "0050", "7ffc",
                "0052", "0054", "0012", "0014", "0016", "0018", "001a", "001c", "001e", "0030", "0032", "7ffc", "0034",
                "0036", "7ffa", "0038", "003a", "003c", "005a", "003e", "0040", "0042", "0044", "0046", "0048", "004a",
                "003a", "003c", "005b", "003e", "0040", "0042", "0044", "0046", "0048", "004a", "003a", "003c", "005c",
                "003e", "0040", "0042", "0044", "0046", "0048", "004a", "003a", "003c", "005d", "003e", "0040", "0042",
                "0044", "0046", "0048", "004a", "004c", "7ffa", "004e", "0050", "7ffc", "0052", "0054", "0020", "0022",
                "0024", "0026", "0028", "002a", "002c", "0030", "0032", "7ffc", "0034", "0036", "7ffa", "0038", "003a",
                "003c", "005e", "003e", "0040", "0042", "0044", "0046", "0048", "004a", "003a", "003c", "005f", "003e",
                "0040", "0042", "0044", "0046", "0048", "004a", "003a", "003c", "0060", "003e", "0040", "0042", "0044",
                "0046", "0048", "004a", "003a", "003c", "0061", "003e", "0040", "0042", "0044", "0046", "0048", "004a",
                "003a", "003c", "0062", "003e","0040", "0042", "0044", "0046", "0048", "004a", "003a", "003c", "0063",
                "003e", "0040", "0042", "0044", "0046", "0048", "004a", "004c", "7ffa", "004e", "0050", "7ffc", "0052",
                "0054", "002e"
        };

        Memoria memoria = new Memoria(dados); //enderecos

        CacheL3P3 cacheL3P3 = new CacheL3P3(3,3,9,memoria);
        CacheL4P2 cacheL4P2 = new CacheL4P2(4,2,9,memoria);

        List<String> memo = memoria.getDadosBin();
        memo.forEach(m -> {
            cacheL3P3.search(m);
            cacheL4P2.search(m);
        });

        System.out.println("\nCache L3P3");
        System.out.println("\tPercentual de acertos: "+cacheL3P3.getPercentualAcertos());
        System.out.println("\tHits: "+cacheL3P3.getHits()+ " Miss: "+cacheL3P3.getMiss());
        cacheL3P3.show();

        System.out.println("\n\n-----------------------------------------------------------------------------------------");
        System.out.println("\nCache L4P2");
        System.out.println("\tPercentual de acertos: "+cacheL4P2.getPercentualAcertos());
        System.out.println("\tHits: "+cacheL4P2.getHits()+ " Miss: "+cacheL4P2.getMiss());
        cacheL4P2.show();
    }
}


