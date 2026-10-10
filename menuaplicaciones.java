import java.util.ArrayList;
import java.util.List;


class NodoGeneral<T> {

    private T dato;
    private List<NodoGeneral<T>> hijos = new ArrayList();

    public NodoGeneral(T dato) {
        this.dato = dato;
    }

    public void agregarhijo(NodoGeneral<T> hijo) {
        hijos.add(hijo);
    }

    public T getDato() {
        return dato;
    }

    public List<NodoGeneral<T>> getHijos() {
        return hijos;
    }

}

public class menuaplicaciones {
    public static void main(String[] args) {
        
        NodoGeneral<String> VisualStudioCode = new NodoGeneral<>("Visual Studio Code"); //raiz, nivel cero
        //nivel 1 hijos de raíz VisualStudioCode
        NodoGeneral<String> File = new NodoGeneral<>("File");
        NodoGeneral<String> Edit = new NodoGeneral<>("Edit");
        NodoGeneral<String> Selection = new NodoGeneral<>("Selection");
        NodoGeneral<String> View = new NodoGeneral<>("View");
        NodoGeneral<String> Go = new NodoGeneral<>("Go");

        //nivel 2 nietos de la raíz, hijos de la carpeta file

        NodoGeneral<String> NewFile = new NodoGeneral<>("New File");
        NodoGeneral<String> Save = new NodoGeneral<>("Save");
        NodoGeneral<String> Share = new NodoGeneral<>("Share");
        
        //nivel 2 nietos de la raiz, hijos de la carpeta edit 

        NodoGeneral<String> Cut = new NodoGeneral<>("Cut");
        NodoGeneral<String> Copy = new NodoGeneral<>("Copy");
        NodoGeneral<String> Paste = new NodoGeneral<>("Paste");

        //nivel 2 nietos de la raiz, hijos de la carpeta Selection
        NodoGeneral<String> SelectAll = new NodoGeneral<>("Select All");
        NodoGeneral<String> CopyLineUp = new NodoGeneral<>("Copy Line Up");
        NodoGeneral<String> MoveLineUp = new NodoGeneral<>("Move Line Up");

        //nivel 2 nietos de la raiz, hijos de la carpeta view

        NodoGeneral<String> Run = new NodoGeneral<>("Run");
        NodoGeneral<String> Terminal = new NodoGeneral<>("Terminal");

        //nivel 2 nieto de la raiz, hijo de la carpeta Go

        NodoGeneral<String> LastEditLocation = new NodoGeneral<>("Last Edit Location");

        //nivel 3 bisnietos de la raiz, nietos de la carpeta file (hijos de share)
        NodoGeneral<String> CopyvsCodedevLink = new NodoGeneral<>("Copy vs CodedevLink");
        NodoGeneral<String> ExportProfileDefault = new NodoGeneral<>("Export Profile Default");


        //ahora hacemos la relación
        //  hijos de la raíz
        VisualStudioCode.agregarhijo(File);
        VisualStudioCode.agregarhijo(Edit);
        VisualStudioCode.agregarhijo(Selection);
        VisualStudioCode.agregarhijo(View);
        VisualStudioCode.agregarhijo(Go);

        //ahora hacemos la relacion, hijos del nivel 2
        File.agregarhijo(NewFile);
        File.agregarhijo(Save);
        File.agregarhijo(Share);

        Edit.agregarhijo(Cut);
        Edit.agregarhijo(Copy);
        Edit.agregarhijo(Paste);

        Selection.agregarhijo(SelectAll);
        Selection.agregarhijo(CopyLineUp);
        Selection.agregarhijo(MoveLineUp);

        View.agregarhijo(Run);
        View.agregarhijo(Terminal);

        //ahora hacemos la relacion nivel 3 bisnietos

        Share.agregarhijo(CopyvsCodedevLink);
        Share.agregarhijo(ExportProfileDefault);

        System.out.println("Nivel 0-Raíz: " + VisualStudioCode.getDato());
        System.out.println();
        System.out.println("Menú de Visual Studio Code: ");
        
        System.out.println(VisualStudioCode.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(VisualStudioCode.getHijos().get(1).getDato());
        System.out.println();
        System.out.println(VisualStudioCode.getHijos().get(2).getDato());
        System.out.println();
        System.out.println(VisualStudioCode.getHijos().get(3).getDato());
        System.out.println();

        System.out.println();
        System.out.println("Opciones de File");
        System.out.println();
        System.out.println(File.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(File.getHijos().get(1).getDato());
        System.out.println();
        System.out.println(File.getHijos().get(2).getDato());
        System.out.println();

        System.out.println("Opciones de Share");
        System.out.println();
        System.out.println(Share.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(Share.getHijos().get(1).getDato());
        System.out.println();



        System.out.println("Opciones de Edit");
        System.out.println();
        System.out.println(Edit.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(Edit.getHijos().get(1).getDato());
        System.out.println();
        System.out.println(Edit.getHijos().get(2).getDato());
        System.out.println();

        System.out.println("Opciones de Selection");
        System.out.println();
        System.out.println(Selection.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(Selection.getHijos().get(1).getDato());
        System.out.println();
        System.out.println(Selection.getHijos().get(2).getDato());
        System.out.println();

        System.out.println("Opciones de View");
        System.out.println();
        System.out.println(View.getHijos().get(0).getDato());
        System.out.println();
        System.out.println(View.getHijos().get(1).getDato());
        System.out.println();

        System.out.println("Opción de GO");
        System.out.println();
        System.out.println(Go.getHijos().get(0).getDato());
        System.out.println();

        ;





        
        

    






    }

}
