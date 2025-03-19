package kotline.xpt.querydslksp.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import kotline.xpt.querydslksp.dto.*
import kotline.xpt.querydslksp.entity.QUser
import org.springframework.stereotype.Repository

@Repository
class ExptQuerydslRepository(
    private val jpaQueryFactory: JPAQueryFactory,
) {
    fun getUsers(): MutableList<UserDataClassConstructorDto> {
        val user = QUser.user

        return jpaQueryFactory.select(
            QUserDataClassConstructorDto(
                user.id,
                user.name
            )
        )
            .from(user)
            .fetch()
    }

    fun getUsers2(): MutableList<UserClassConstructorDto> {
        val user = QUser.user

        return jpaQueryFactory.select(
            QUserClassConstructorDto(
                user.id,
                user.name
            )
        )
            .from(user)
            .fetch()
    }

    fun getUsers3(): MutableList<UserClassDto> {
        val user = QUser.user

        return jpaQueryFactory.select(
            QUserClassDto(
                user.id,
                user.name
            )
        )
            .from(user)
            .fetch()
    }
}