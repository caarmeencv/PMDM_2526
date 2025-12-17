package com.example.examen;

import java.io.Serializable;
import java.util.ArrayList;

public class Personaje implements Serializable {

    //Carmen Calo Vazquez
    private int valoracion, apariciones, imagen;
    private String nombre, tipo, descripcion;


    public Personaje(int valoracion, int apariciones, String nombre, String tipo, int imagen, String descripcion) {
        this.valoracion = valoracion;
        this.apariciones = apariciones;
        this.imagen = imagen;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getApariciones() {
        return apariciones;
    }

    public void setApariciones(int apariciones) {
        this.apariciones = apariciones;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    static ArrayList<Personaje> getDatos() {
        ArrayList<Personaje> personajes = new ArrayList<>();
        personajes.add(new Personaje(5, 120, "Ben Duende", "Duende", R.drawable.ben, "Ben es un duende y uno de los personajes principales junto a Holly Cardo. Sus padres son el Sr. y la Sra. Duende. Vive en el Gran Árbol Duende con el Viejo Duende Sabio en la parte superior. Ben tiene una camisa azul, un par de pantalones cortos color azul y un sombrero triangular azul con una hoja que sobresale."));
        personajes.add(new Personaje(5, 120, "Princesa Holly", "Hada", R.drawable.holly, "Holly es un hada y su nombre verdadero es Holly Cardo. Es una de los personajes principales junto a Ben. A ella le gusta usar la 1magia a pesar del riesgo de que las cosas no salgan bien. Su mejor amigo es Ben Duende y sus padres son el rey y la reina Cardo. Lleva un vestido rosa y una pequeña corona."));
        personajes.add(new Personaje(5, 105, "Nana Ciruela", "Hada", R.drawable.nana, "Nana Ciruela es la ama de llaves del pequeño castillo, hechicera, maestra y la mejor amiga adulta de la Princesa Holly, ésta nana le enseña muchos hechizos, se pelea siempre con los duendes, y casi siempre, le enseña a Holly a hacer trampa con magia, además de que es querida por parte del Duende tío de Barnavit, el cual siempre le besa la mano, pero Nana se limpia con su ropa, la cual siempre es morada. Casi siempre es bravucona y siempre provoca la inundación de gelatina, jalea o helado, y en un episodio es despedida por el Rey Cardo por hacer nevar dentro del castillo, y le quita su varita por no controlar sus hechizos, no se sabe el nombre completo de ella, Nana a veces no obedece lo que los Reyes le dicen."));
        personajes.add(new Personaje(3, 100, "Rey Cardo", "Hada", R.drawable.rey, "És el rey del pequeño reino. Esposo de la reina Cardo. Su nombre verdadero es Rey Cardo. También es el padre de Holly y sus Hermanitas Margarita y Amapola, en el episodio La ropa nueva del Rey Cardo el todavía se baña con un patito de hule, aunque dice llamarse así mismo 'Rey Cardo' nunca se ha revelado su nombre, se cree que 'Cardo' sea su nombre, pero en el episodio 'Renacuajos', le grita a Holly; Holly Cardo por lo que se dice que si tiene nombre pero no se ha dicho, en cuanto a la Familia Real Cardo, se sabe muy poco pero si nos fijamos muy bien en la sala podemos apreciar dos cuadros en donde hay un Rey Anciano y una Reina un poco joven aún, se cree que puedan ser los padres del Rey Cardo, También hay fotografías de mujeres con corona con vestidos diferentes por lo que se dice que el Rey pueda tener hermanas, en cuanto a su edad se sabe muy poco ya que en el episodio 'el cumpleaños del Rey Cardo' Nana Ciruela dice que necesitaba más velas quizá pueda ser que haya sido una broma ó se tratara simplemente de la realidad, además no llama al la Reina Cardo 'esposa' sino que dice 'Reina Cardo, aunque si su nombre se dice junto se dice 'ReyCardo' da la ilusión que el nombre daría Ricardo y ese podría ser su nombre completo, y el enigma de su nombre seria completamnte descubierto y su nombre sería 'Rey Ricardo Cardo'."));
        personajes.add(new Personaje(4, 102, "Reina Cardo", "Hada", R.drawable.reina, "La reina Cardo es la madre de Holly y sus hermanas Margarita y Amapola. También es hermana de la Reina Caléndula y es la esposa del Rey Cardo. Ella es la reina del Reino, no se sabe su nombre completo. Véase arriba 'Rey Cardo'"));
        personajes.add(new Personaje(2, 107, "Señor duende", "Duende", R.drawable.sduende, "Es el padresde Ben Duende. Era Marinero. Tubo varios barcos como: Hilda, Doris, Peggy, Abigail, Fifi, Trixibelle, Sabrina, Vicky, Señora Boo Boo y Bunty y todos el Gran y Malvado Barrie se los comió."));
        personajes.add(new Personaje(5, 109, "Viejo Duende Sabio", "Duende", R.drawable.viejosabio, "Es un duende muy sabio y tiene una edad aproximada de 250 años, el siempre dice a Nana Ciruela; ¡Nada de magia Señora Ciruela! suele decir, él es el médico en el episodio 'El rey no se siente bien', sastre en 'La ropa nueva del Rey Cardo', profesor en los episodios donde Ben y Holly van al colegio duende, arquitecto en 'la visita de Gastón', bibliotecario en 'Libros', Y mago pésimo el entretenedor duende llamado El Gran Hechicerardo en 'La fiesta, y es el director de la fábrica duende, casi en todos los episodios, él tiene múltiples cargos y oficios. El no es lleva bien con las hadas, Siempre hace que Nana Ciruela se enfade o se ponga tímida con algunos de sus comentarios, él vive en el apartamento duende número 98, y además él tiene un hermano gemelo que es mayo que el y que habita en el Polo Norte por eso ha salido en un capítulo y el hermano del Viejo Duende Sabio afirma que es mayor 3 minutos antes que el y supuestamente el Viejo Duende Sabio viajó hasta el pequeño reino para ser el Líder de los duendes alla en cambio su hermano se quedó en el Polo Norte. Su nombre verdadero es Cedric."));
        personajes.add(new Personaje(1, 24, "Rey Caléndula", "Hada", R.drawable.calendula, "Es el Rey de otro reino. El y su mujer se visten muy locos y cada vez que llegan al pequeño castillo dicen; És un perfecto ejemplo de la vida campestre o Siempre es un placer volver a visitar su pequeño reino o ¡És la Moda Campestre! Los Reyes Caléndula son parientes de la familia real Cardo. Reina Cardo los describe como 'muy de moda.'"));
        personajes.add(new Personaje(4, 54, "Gastón", "Otro", R.drawable.gaston, "És el mejor amigo insecto de Ben y Holly él es una mariquita de sexo masculino que ladra como un perro y se nota su semblante en su rostro Ben siempre suele montar en él. Algunas veces Nana Ciruela es su traductora. Él sabe hablar Hormiga."));
        personajes.add(new Personaje(2, 23, "Violeta", "Hada", R.drawable.sinimagen, "Es un hada que es una de las amigas de Holly. Ella lleva un vestido violeta."));
        personajes.add(new Personaje(3, 36, "Flor", "Hada", R.drawable.sinimagen, "es un hada que es una de las amigas de Holly. Ella lleva un vestido rojo y un lazo rojo."));
        personajes.add(new Personaje(4, 45, "Lucy", "Humano", R.drawable.lucy, "es una niña del Gran Reino, que ha sido invitada al pequeño reino de Ben y Holly para que vea cómo viven allí"));
        personajes.add(new Personaje(4, 15, "El pirata Barbarroja", "Duende", R.drawable.pirata, "Tío pirata de Barnabit. Afirma ser un 'buen' pirata. Él es muy cortés, pero a veces más lo hace, como besar la palma de Nana Ciruela cada vez que habla con ella. Lleva un sombrero de pirata."));
        personajes.add(new Personaje(5, 12, "El Gran y malvado Barry", "Otro", R.drawable.barry, "Pez malvado que habita en el Lago y se ha comido todos los barcos del Sr Duende."));
        personajes.add(new Personaje(2, 12, "Tarquino", "Hada", R.drawable.tarquino, "Amigo de Margarita y Amapola y suele decir, 'A Tarquino le gusta fiesta', y el Rey Cardo asegura Tarquino es un monstruo."));
        personajes.add(new Personaje(2, 14, "Sra Bruja", "Humano", R.drawable.bruja, " Una bruja que no ha lanzado un hechizo desde hace 100 años."));
        personajes.add(new Personaje(3, 16, "Betty Oruga", "Otro", R.drawable.betty, "Luego pasa a Ser Betty Mariposa"));
        return personajes;
    }
}



