package mergeestudo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import mergeestudo.Arquivo_Java;

public class Merge
{
    private int vet[],vet1[],vet2[];
    private int tl;
    private No ini, fim;
    private Arquivo_Java arq;
  //alterado essa linha
    public Merge()
    {
        vet = new int[100];//100
        tl=0;
        ini = fim = null;
        arq = new Arquivo_Java("C:\\Users\\Daniel\\Desktop\\arq.dat");
    }

    public void leVetor()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Tamanho do Vetor");
        tl = sc.nextInt();
        for (int i=0; i< tl; i++)
        {
            System.out.println("Digite o "+i+"º numero");
            vet[i] = sc.nextInt();
        }
    }

    public void exibeVetor()
    {
        System.out.println("Conteudo do vetor: ");
        for (int i=0; i<tl; i++)
        {
            System.out.println(vet[i]);
        }
    }

    public void ordena_Vetor()
    {
        vet1 = new int[tl/2];
        vet2 = new int[tl/2];
        int seq = 1;
        while (seq < tl)
        {
          Particao();
          Fusao(seq);
          seq *= 2;
        }
    }

    public void Particao()
    {
      int tam = tl/2;
      for (int i=0; i < tam; i++)
      {
        vet1[i] = vet[i];
        vet2[i] = vet[i+tam];
      }
    }

    public void Fusao(int seq)
    {
      int i=0, j=0, k=0, tseq=seq;

      while (k < tl)
      {
          while (i < seq && j < seq)
          {
            if (vet1[i] < vet2[j])
            {
              vet[k] = vet1[i];
              i++;
            }
            else
            {
              vet[k] = vet2[j];
              j++;
            }
            k++;
          }

        //poe td mundo que sobrou da sequencia que nao estourou
          while (i < seq)
          {
            vet[k] = vet1[i];
            k++;
            i++;
          }
          while (j < seq)
          {
            vet[k] = vet2[j];
            k++;
            j++;
          }
          seq += tseq;
      }
    }

    public void insereLista(int elemento)
    {
        No nc = new No(fim,null,elemento);

        if (ini == null) //a lista ta vazia, entao fim == inicio == null
        {
            ini = fim = nc;
        }
        else               // jah tem gente na lista...
        {
            fim.setProx(nc);
            fim = nc;
        }
    }

    public void leLista()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite um número (0 termina):");
        int num = sc.nextInt();
        while (num > 0)
        {
            insereLista(num);
            System.out.println("Digite um número (0 termina):");
            num = sc.nextInt();
        }
    }

    public void exibeLista()
    {
        No aux = ini;
        System.out.println("Conteúdo da Lista");
        while (aux != null)
        {
            System.out.println(aux.getInfo());
            aux = aux.getProx();
        }
    }

    public void ordena_Lista()
    {
    }

    public void leArq()
    {
        arq.leArq();
    }

    public void exibeArq()
    {
        arq.exibirArq();
    }

    public void ordena_Arq()
    {
        vet1 = new int[tl/2];
        vet2 = new int[tl/2];
        int seq = 1;
        while (seq < tl)
        {
          ParticaoArq();
          FusaoArq(seq);
          seq *= 2;
        }
    }

    public void ParticaoArq()
    {
      int tam = tl/2;
      for (int i=0; i < tam; i++)
      {
        vet1[i] = vet[i];
        vet2[i] = vet[i+tam];
      }
    }

    public void FusaoArq(int seq)
    {
      int i=0, j=0, k=0, tseq=seq;

      while (k < tl)
      {
          while (i < seq && j < seq)
          {
            if (vet1[i] < vet2[j])
            {
              vet[k] = vet1[i];
              i++;
            }
            else
            {
              vet[k] = vet2[j];
              j++;
            }
            k++;
          }

        //poe td mundo que sobrou da sequencia que nao estourou
          while (i < seq)
          {
            vet[k] = vet1[i];
            k++;
            i++;
          }
          while (j < seq)
          {
            vet[k] = vet2[j];
            k++;
            j++;
          }
          seq += tseq;
      }
    }

   
}