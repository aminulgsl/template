package template
import org.springframework.security.access.annotation.Secured
class DemoController {

    @Secured('permitAll')
    def index() {
    render (view: 'dataTable')
    }

    @Secured('permitAll')
    def emptyPage(){
        render (view: '/emptyPage')
    }

    @Secured('permitAll')
    def jqGrid(){
        render (view: 'jqGrid')
    }
}
