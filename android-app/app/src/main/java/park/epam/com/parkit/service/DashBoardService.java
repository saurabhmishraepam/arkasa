package park.epam.com.parkit.service;

import park.epam.com.parkit.dto.DashboardDto;

public class DashBoardService {

    private DashboardDto dashboardDto;

    public DashboardDto getDashboardDto() {
        return dashboardDto;
    }

    public void setDashboardDto(DashboardDto dashboardDto) {
        this.dashboardDto = dashboardDto;
    }
}
