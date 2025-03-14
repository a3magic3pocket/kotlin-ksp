package kotline.xpt.querydslksp.dto

import com.querydsl.core.annotations.QueryProjection

data class UserDto @QueryProjection constructor(
    val id: Long,
    val name: String,
)