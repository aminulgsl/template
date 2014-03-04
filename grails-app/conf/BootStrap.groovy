import com.gsl.uma.security.Role
import com.gsl.uma.security.User
import com.gsl.uma.security.UserRole

class BootStrap {

    def init = { servletContext ->
    }
    void createUserWithRole(){
        Role superAdmin = Role.findByAuthority("ROLE_SUPER_ADMIN")
        if(!superAdmin){
            superAdmin = new Role(authority: 'ROLE_SUPER_ADMIN').save(flush: true)
//            superAdmin.id = 100; superAdmin.save(flush: true)
        }

        User adminUser = User.findByUsername('admin')
        if(!adminUser){
            adminUser = new User(username: 'admin', password: 'password', enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false)
            adminUser.save(flush: true)

            new UserRole(user: adminUser, role: superAdmin).save(flush: true)
        }

    }
    def destroy = {
    }
}
