package kotline.xpt.querydslksp.dto

import com.querydsl.core.annotations.QueryProjection

data class UserDataClassConstructorDto @QueryProjection constructor(
    val id: Long,
    val name: String,
)

class UserClassConstructorDto @QueryProjection constructor(
    val id: Long,
    val name: String
)

@QueryProjection
class UserClassDto (val id: Long, val name: String)

//@QueryProjection
//data class UserDto(
//    val id: Long,
//    val name: String,
//)

//class UserDto (
//    val id: Long,
//    val name: String,
//
//    @QueryProjection
//    fun hello() {
//
//    }
//)
