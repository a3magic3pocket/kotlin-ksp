package kotline.xpt.querydslksp.entity

import jakarta.persistence.*

@Entity
@Table(name = "`users`")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "age")
    val age: Int,
)
