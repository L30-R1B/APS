package com.prisao.controle.gerenciamento.pessoa;

import com.prisao.modelo.local.Cela;
import com.prisao.modelo.pessoa.Prisioneiro;

public class GerenciaPrisioneiros {
    public static Prisioneiro pesquisarPrisioneiro(int identificador){
        Cela cela = Cela.getInstancia();

        for(Prisioneiro prisioneiroAtual :cela.getPrisioneiros()){
            if(prisioneiroAtual.getIdentificador() == identificador){
                return prisioneiroAtual;
            }
        }
        
        return null;
    }

    public static boolean cadastrarPrisioneiro(int identificadorPrisioneiro, String nome, String crime, float pena, String comportamento){
        if(pesquisarPrisioneiro(identificadorPrisioneiro) != null){
            return false;
        }

        Cela cela = Cela.getInstancia();
        
        return cela.inserePrisioneiro(new Prisioneiro(identificadorPrisioneiro, nome, crime, pena, comportamento));
    }

    public static boolean desvincularPrisioneiro(int identificador){
        Prisioneiro prisioneiro = pesquisarPrisioneiro(identificador);
        if(prisioneiro == null){
            return false;
        }

        Cela cela = Cela.getInstancia();

        return cela.getPrisioneiros().remove(prisioneiro);
    }
}
