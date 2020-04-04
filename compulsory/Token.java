public class Token {
    private int size;       //marimea maxima a numarului ce poate fi introdus pe token
    private int number;     //numarul de pe token

    public Token(int size) {
        this.size = size;
    }

    public void setNumber(int number) {
        if (number > size)
            this.number = -1; //blank token
        else
            this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
