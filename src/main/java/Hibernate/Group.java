package Hibernate;
import javax.persistence.*;

@Entity   //маппинг для базы данных
@Table(name="groop") //к какой таблице
public class Group {
    @Id // колонка с первичным ключем
    //@GeneratedValue(strategy = GenerationType.IDENTITY) чтобы автоматом увеличивала на 1
    @Column (name="id")
    private int id; //все поля приватными и геттеры и сеттеры, конструктор без параметров и поля приватными
    //тогда класс щитается хипернейт классом

    @Column (name="Title")
    private String title;

    @Column (name="room")
    private int room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", room=" + room +
                '}';
    }
}
