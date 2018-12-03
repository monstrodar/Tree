/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergeestudo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 *
 * @author Daniel
 */
public class Arquivo_Java {
    private String nomearquivo;
  private RandomAccessFile arquivo;

  public Arquivo_Java(String nomearquivo)
  {
    this.nomearquivo = nomearquivo;
    try
    {
      arquivo = new RandomAccessFile(this.nomearquivo,"rw");
    }
    catch(IOException e){}
  }

  public void truncate(int pos) //desloca eof
  {
    try
    {
      arquivo.setLength(pos*Registro.length());
    }
    catch(IOException exc) { }
  }

  //igual ao eof do Pascal
  public boolean eof()      //se o ponteiro esta no eof
  {
    boolean retorno = false;
    try
    {
      if (arquivo.getFilePointer()==arquivo.length())  //retorna posicao em bytes
        retorno = true;
    }
    catch(IOException e){}
    return retorno;
  }

  //insere um Registro no final do arquivo, passado por parâmetro
  public void inserirRegNoFinal(Registro reg)
  {
    try
    {
      //arquivo.seek(arquivo.length());//ultimo byte
      seekArq(filesize());
      reg.gravaNoArq(arquivo);
    }
    catch(Exception e){}
  }

  public void inserirPos(Registro reg, int pos)
  {
    try
    {
      seekArq(pos);
      reg.gravaNoArq(arquivo);
    }
    catch(Exception e){}
  }

  public int filesize()
  {
    try
    {
      return (int)(arquivo.length() / Registro.length());
    }
    catch(IOException e){}
    return 0;
  }

  public void exibirArq()
  {
    int i;
    Registro aux = new Registro();
    try
    {
      arquivo.seek(0);
	  i=0;
	  while(!this.eof())
	  {
	     System.out.println("Posicao " + i);
		 aux.leDoArq(arquivo);
		 aux.exibirReg();
		 i++;
	  }
	}
	catch(IOException e){}
  }

  public void exibirUmRegistro(int pos)
  {
    Registro aux = new Registro();
    try
    {
        arquivo.seek(pos*Registro.length());
        System.out.println("Posicao " + pos);
        aux.leDoArq(arquivo);
        aux.exibirReg();
    }
    catch(IOException e){}
  }

  public Registro getReg(int pos)
  {
      seekArq(pos);
      Registro r = new Registro();
      r.leDoArq(arquivo);
      return r;
  }

  public void seekArq(int pos)
  {
    try
    {
      arquivo.seek(pos*Registro.length());
    }
    catch(IOException e){}
  }

  public void leArq()
  {
    int codigo,idade;
    String nome;

    codigo=Entrada.leInteger("Digite o código");
    while (codigo!=0)
    {
      nome=Entrada.leString("Digite o nome");
      idade=Entrada.leInteger("Digite a idade");
      inserirRegNoFinal(new Registro(codigo,nome,idade));
      codigo=Entrada.leInteger("Digite o código");
    }
  }

  public int filepos()
  {
    int pos = -1;
    try
    {
        pos = (int)(arquivo.getFilePointer() / Registro.length());
    }
    catch (IOException e){}
    return pos;
  }

  public int getCodigo(int pos)
  {
      int posicaoAnterior = filepos();

      seekArq(pos);
      Registro r = new Registro();
      r.leDoArq(arquivo);

      seekArq(posicaoAnterior);

      return r.getCodigo();
  }

  public void copiaReg(int de, int para)
  {
      Registro r = new Registro();
      seekArq(de);
      r.leDoArq(arquivo);
      seekArq(para);
      r.gravaNoArq(arquivo);
  }

  public void trocaReg(int de, int para)
  {
      Registro r = new Registro();
      Registro r2 = new Registro();

      //pega o primeiro em poe em r
      seekArq(de);
      r.leDoArq(arquivo);

      //pega o segundo e poe em r2
      seekArq(para);
      r2.leDoArq(arquivo);

      //grava r2 em de
      seekArq(de);
      r2.gravaNoArq(arquivo);

      //grava r em para
      seekArq(para);
      r.gravaNoArq(arquivo);
  }

}


//... classe Entrada (possui métodos para entrada de dados) ....
class Entrada
{
  public static String leString(String msg)
  {
    String line="";
    InputStreamReader isr= new InputStreamReader(System.in);
    BufferedReader br= new BufferedReader(isr);
    try
    {
      System.out.println(msg);
      line = br.readLine();
    }
    catch(Exception e){}
    return line;
  }

  public static int leInteger(String msg)
  {
    String line="";
    InputStreamReader isr= new InputStreamReader(System.in);
    BufferedReader br= new BufferedReader(isr);
    try
    {
      System.out.println(msg);
      line=br.readLine();
      int retorno=Integer.valueOf(line).intValue();
      return retorno;
    }
    catch(Exception e)
    {
      return -1;
    }
  }
}


//... classe Registro (será um objeto que simboliza o registro em pascal) ....
class Registro
{
  public final int tf=20;//variavel do tipo "final" é constante
  private int codigo, tl=tf, idade;
  private char nome[] = new char[tf];

  public Registro(){}

  public Registro(int codigo, String Snome, int idade)
  {
    this.codigo=codigo; //this é variavel de estancia
    this.idade=idade;
    this.tl=Snome.length();
    for (int i=0 ; i<Snome.length() ; i++)
    {
      nome[i]=Snome.charAt(i);
    }
  }

  public int getCodigo()
  {
    return(codigo);
  }

  public String getNome()
  {
   	String Snome = new String(nome);
    return(Snome);
  }

  public void gravaNoArq(RandomAccessFile arquivo)
  {
    int i;
	try
	{
	  arquivo.writeInt(codigo);
	  arquivo.writeInt(idade);
	  arquivo.writeInt(tl);
	  for(i=0;i<tf;i++)
		arquivo.writeChar(nome[i]);
	}
	catch(IOException e){ System.out.println("PAU!!"); }
  }

  public void leDoArq(RandomAccessFile arquivo)
  {
	int i;
	try
	{
	  this.codigo = arquivo.readInt();
	  this.idade = arquivo.readInt();
	  this.tl = arquivo.readInt();
	  for(i=0;i<this.tf;i++)
	    this.nome[i] = arquivo.readChar();
	  for(i=this.tl ; i<tf ; i++)
	    this.nome[i] =' ';
	}
	catch(IOException e){ System.out.println("PAU!!"); }
  }

  public void exibirReg()
  {
	System.out.print("codigo....."+this.codigo);
	System.out.print(" nome.......");
	String Snome = new String(nome);
	System.out.print(Snome);
	System.out.println(" idade......."+this.idade);
	System.out.println("----------------------------------");
  }

  static int length()
  {
  	return(52); //int codigo, tl=20, idade; ------------> 12 bytes
                //private char nome[] = new char[20]; --> 40 bytes
                //------------------------------------------------- +
                //                      Total : 40 + 12 = 52 bytes
  }


}
