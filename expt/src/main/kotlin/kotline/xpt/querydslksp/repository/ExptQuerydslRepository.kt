package kotline.xpt.querydslksp.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import kotline.xpt.querydslksp.dto.QUserDto
import kotline.xpt.querydslksp.dto.UserDto
import kotline.xpt.querydslksp.entity.QUser
import org.springframework.stereotype.Repository

@Repository
class ExptQuerydslRepository(
    private val jpaQueryFactory: JPAQueryFactory,
) {
    fun getUsers(): MutableList<UserDto> {
        val user = QUser.user

        return jpaQueryFactory.select(
            QUserDto(
                user.id,
                user.name
            )
        )
            .from(user)
            .fetch()
    }
}