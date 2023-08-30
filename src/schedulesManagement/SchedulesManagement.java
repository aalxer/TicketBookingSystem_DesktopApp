package schedulesManagement;

import schedulesManagement.data.Schedules;
import salesMangement.enume.Cities;

import java.io.IOException;

public interface SchedulesManagement {

    void add(Schedules ticket) throws IOException, TicketExistsException;
    void delete(int index) throws MemoryEmptyException, IndexOutOfBoundsException, IOException;
    void printAll() throws MemoryEmptyException;
    Schedules getTicketByIndex(int index) throws MemoryEmptyException;
    Schedules getTicketByCities(Cities stadt1, Cities stadt2) throws MemoryEmptyException;
}
