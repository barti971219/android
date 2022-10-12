package com.hee.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.room.*
import androidx.room.Database
import androidx.room.OnConflictStrategy.REPLACE
import org.w3c.dom.Text

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        // 기본적으로 데이터베이스 작업은 메인 쓰레드에서 할 수 없다
        // 이유는, 데이터베이스 작업을 휴대폰이 하는 동안 사용자가 기다려야하기 때문
        // 해결책은, 쓰레드를 이용하거나 async를 이용하면 된다
        val database = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "user-database"

        ).allowMainThreadQueries() // 메인쓰레드에서도 데이터 작업을 할 수 있게 해주는 메소드
            .build()

        findViewById<TextView>(R.id.save).setOnClickListener{

            val userProfile = UserProfile("홍", "길동")
            database.userProfileDao().insert(userProfile)
        }

        findViewById<TextView>(R.id.load).setOnClickListener{

            val userProfiles = database.userProfileDao().getAll()
            userProfiles.forEach{
                Log.d("testt", "" + it.id + it.firstName)
            }
        }

        findViewById<TextView>(R.id.delete).setOnClickListener{
            database.userProfileDao().delete(1)
        }


    }
}

@Database(entities = [UserProfile::class], version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userProfileDao(): UserProfileDao

    // database_one
    //      UserProfile
    //      UserNumber
    // database_two
    //      CustomerInfo
}

@Dao
interface UserProfileDao {
    // CRUD -> 데이터 베이스 조작
    // Query -> 데이터 베이스 조회
    @Insert(onConflict = REPLACE)
    fun insert(userProfile: UserProfile)

    @Query("DELETE FROM userprofile WHERE id = :userId")
    fun delete(userId: Int)

    @Query("SELECT * FROM userprofile")
    fun getAll() : List<UserProfile>

}

@Entity
class UserProfile(

    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "last_name")
    val lastName : String,
    @ColumnInfo(name = "first_name")
    val firstName : String
){
    constructor(lastName: String, firstName: String): this(id=0, lastName, firstName)
}

