package template

import com.gsl.oros.demo.Person
import grails.converters.JSON
import org.springframework.security.access.annotation.Secured

class DemoController {

    @Secured('permitAll')
    def index() {
        render(view: 'dataTable')
    }

    @Secured('permitAll')
    def emptyPage() {
        render(view: '/emptyPage')
    }

    @Secured('permitAll')
    def jqGrid() {
        render(view: 'jqGrid')
    }

    @Secured('permitAll')
    def demo() {
        render(view: 'demo')
    }

    @Secured('permitAll')
    def jqGridList() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Md. Rasel", "Hosen", "Dhaka"));
        personList.add(new Person("Md. Imran", "Hosen", "Gulsan"));
        personList.add(new Person("Md. Shahid", "Gazi", "Banani"));
        personList.add(new Person("Nahida", "Sultana", "Uttara"));
        personList.add(new Person("Farhad", "Khan", "Dhanmodi"));
        personList.add(new Person("Jakir", "Hasan", "Baridhara"));
        personList.add(new Person("Sohel", "Ahemd", "Mohammadpur"));
        personList.add(new Person("Jakia", "Sultana", "Mirpur"));


        int recordsCount = personList.size()
        int maxRows = Integer.valueOf(params.rows ?: 5)
        int numberOfPages = Math.ceil(recordsCount / maxRows)
        Map gridOutput = [page: 1, records: recordsCount, total: numberOfPages, rows: personList]
        render (gridOutput as JSON)

    }

}
