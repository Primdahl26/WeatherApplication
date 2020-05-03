package Primdahl.Mandatory3.Repository;

import Primdahl.Mandatory3.Model.WeatherSummary;
import org.springframework.data.repository.CrudRepository;

public interface WeatherSummaryRepository extends CrudRepository<WeatherSummary, Long> {
}
