import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManagerFactory
import kotline.xpt.querydslksp.dto.*
import kotline.xpt.querydslksp.entity.QUser
import kotline.xpt.querydslksp.entity.User
import org.hibernate.cfg.AvailableSettings
import org.hibernate.cfg.Configuration
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.assertNotNull

class Tests {
    @Test
    fun `select entity`() {
        val emf = initialize()

        run {
            try {
                val em = emf.createEntityManager()
                em.transaction.begin()
                em.persist(User(null,"John Smith", 50))
                em.transaction.commit()
                em.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        run {
            val em = emf.createEntityManager()
            val queryFactory = JPAQueryFactory(em)
            val q = QUser.user
            val userDto: UserDataClassConstructorDto? = queryFactory
                .select(QUserDataClassConstructorDto(
                    q.id,
                    q.name
                ))
                .from(q)
                .fetchOne()
            assertNotNull(userDto)
            assertThat(userDto.id).isEqualTo(1)
            assertThat(userDto.name).isEqualTo("John Smith")
            em.close()
        }

        run {
            val em = emf.createEntityManager()
            val queryFactory = JPAQueryFactory(em)
            val q = QUser.user
            val userDto: UserClassConstructorDto? = queryFactory
                .select(QUserClassConstructorDto(
                    q.id,
                    q.name
                ))
                .from(q)
                .fetchOne()
            assertNotNull(userDto)
            assertThat(userDto.id).isEqualTo(1)
            assertThat(userDto.name).isEqualTo("John Smith")
            em.close()
        }

        run {
            val em = emf.createEntityManager()
            val queryFactory = JPAQueryFactory(em)
            val q = QUser.user
            val userDto: UserClassDto? = queryFactory
                .select(QUserClassDto(
                    q.id,
                    q.name
                ))
                .from(q)
                .fetchOne()
            assertNotNull(userDto)
            assertThat(userDto.id).isEqualTo(1)
            assertThat(userDto.name).isEqualTo("John Smith")
            em.close()
        }
    }

    private fun initialize(): EntityManagerFactory {
        val configuration = Configuration()
            .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect") // 사용할 DB Dialect 설정
            .setProperty("hibernate.connection.driver_class", "org.h2.Driver") // H2 JDBC 드라이버 설정
            .setProperty("hibernate.connection.url", "jdbc:h2:mem:my-database") // H2 메모리 DB URL
            .setProperty(AvailableSettings.HBM2DDL_AUTO, "create-drop")
            .setProperty(AvailableSettings.SHOW_SQL, "true")
            .addAnnotatedClass(User::class.java)

        return configuration
            .buildSessionFactory()
            .unwrap(EntityManagerFactory::class.java)
    }
}