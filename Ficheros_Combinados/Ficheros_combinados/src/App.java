
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        
        Scanner teclado = new Scanner(System.in);
		String opcion = "";
		final String pathFile = "./Recursos/";
		final String fileNameBinario = "almacen.dat";
		final String fileNameUnicode = "productos.csv";

        LinkedList<Producto> productosLeidos = leerFicheroUnicode(pathFile, fileNameUnicode);

		do { 
			System.out.println("1. Mostrar Productos en el Inventario.");
			System.out.println("2. Eliminar Producto por referencia.");
			System.out.println("3. Guardar y Salir (inventario.dat).");
			System.out.println("4. Salir");
			teclado = new Scanner(System.in);
			opcion = teclado.nextLine();

			switch (opcion) {
				case "1":
					
					break;

				case "2":
					
					break;

				case "3":
					
					break;

				case "4":
					System.out.println("Saliendo del programa, HASTA PRONTO");
					break;

				default:
					System.out.println("Elegir el numero correcto");
			}
		} while (!opcion.equalsIgnoreCase("4"));

		int i = 0;
		boolean eof = false; 
		
		try(FileInputStream fichero = new FileInputStream(pathFile+fileNameBinario);
				DataInputStream lector = new DataInputStream(fichero);)
		{
			while(!eof)
			{

				int cantidad = lector.readInt();
				double precio = lector.readDouble();
				int descuento = lector.readInt();
				int IVA = lector.readInt();
				boolean aplicarDto = lector.readBoolean();

				productosLeidos.get(i).setCantidad(cantidad);
				productosLeidos.get(i).setPrecio(precio);
				productosLeidos.get(i).setDescuento(descuento);
				productosLeidos.get(i).setIVA(IVA);
				productosLeidos.get(i).setAplicarDto(aplicarDto);
				
			}
			
		}catch(EOFException e)
		{
			eof = true;

			
		}catch(IOException e)
		{
			System.out.println("Ha ocurrido un error al I/O");
			System.out.println(e.getMessage());
			
		}catch(Exception e)
		{
			System.out.println("Ha ocurrido un error inexperado");
			System.out.println(e.getMessage());
		}

    }
	private static LinkedList<Producto> leerFicheroUnicode(final String pathFile, String fileName) {
		LinkedList<Producto> lineas = null;
		if(pathFile != null && fileName != null)
		{
			lineas = new LinkedList<>();
			
			try (FileReader file = new FileReader(pathFile+fileName);
					BufferedReader buffer = new BufferedReader(file);)
			{
					String linea = null;
					do {
						linea = buffer.readLine();
						
						if(linea != null) {
							
							String [] elementos = linea.split("/");
							Producto p = 
									new Producto(elementos[0], elementos[1], elementos[2],Integer.parseInt(elementos[3]),
									Double.parseDouble(elementos[4]), Integer.parseInt(elementos[5]), Integer.parseInt(elementos[6]),
									Boolean.parseBoolean(elementos[7]));
							lineas.add(p);
						}

					}while(linea != null);

					return lineas;

			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return lineas;
}
private static void escribirFicheroBinarioExamen(final String pathFile, String fileNameBinario) {
		
		try(FileOutputStream fichero = new FileOutputStream(pathFile+fileNameBinario, false);
				DataOutputStream escritor = new DataOutputStream(fichero);)
		{
			
			Producto p1 = new Producto(5,25.75,15,21,false);
			Producto p2 = new Producto(15,55.95,5,21,true);
			Producto p3 = new Producto(100,3.25,0,21,false);
			Producto p4 = new Producto(300,0.95,0,21,false);
			Producto p5 = new Producto(27,5.25,13,21,true);
			
			
			LinkedList<Producto> productos = new LinkedList<Producto>();
			
			productos.add(p1);
			productos.add(p2);
			productos.add(p3);
			productos.add(p4);
			productos.add(p5);
			
			
			for(Producto p : productos)
			{

				escritor.writeInt(p.getCantidad());
				escritor.writeDouble(p.getPrecio());
				escritor.writeInt(p.getDescuento());
				escritor.writeInt(p.getIVA());
				escritor.writeBoolean(p.isAplicarDto());
			}
	
		}catch(IOException e) {
			System.out.println("Ha ocurrido un error al I/O");
			System.out.println(e.getMessage());
			
		}catch(Exception e)
		{
			System.out.println("Ha ocurrido un error inexperado");
			System.out.println(e.getMessage());

		}
	}

}

