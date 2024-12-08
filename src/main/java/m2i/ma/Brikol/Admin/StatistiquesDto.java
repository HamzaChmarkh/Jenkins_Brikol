package m2i.ma.Brikol.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatistiquesDto {
    private long totalUsers;
    private long totalFreelancers;
    private long totalClients;
    private long totalServices;
    private long totalCategories;
    // add more if need
}
