package first;

public class book {
String book_name;
int id;
String author_name;
int price;
String genre;
int published_year;
 book(String book_name,int id,String author_name,int price,String genre,int published_year) {
	this.book_name=book_name;
	this.id=id;
	this.author_name=author_name;
	this.price=price;
	this.genre=genre;
	this.published_year=published_year;
}
	public String getBook_name() {
	return book_name;
}
public void setBook_name(String book_name) {
	this.book_name = book_name;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAuthor_name() {
	return author_name;
}
public void setAuthor_name(String author_name) {
	this.author_name = author_name;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getGenre() {
	return genre;
}
public void setGenre(String genre) {
	this.genre = genre;
}
public int getPublished_year() {
	return published_year;
}
public void setPublished_year(int published_year) {
	this.published_year = published_year;
}
	public static void main(String[] args) {
		book Book=new book("IKIGAI",201,"JOHN",562,"inspirational",2005);
		System.out.println(Book.getAuthor_name());
	}

}
