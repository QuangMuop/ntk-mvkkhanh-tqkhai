package util;
import java.util.ArrayList;
import java.util.List;

public class PagingHelper {
	public static List<Integer> PagingCaculator(int currentPage, int recordsPerPage, long totalRecords)
    {
        List<Integer> pagingNumbers = new ArrayList<Integer>();
        int pagesPerView = 9;
        int startIndex = 1;
        int lastIndex = 9;
        int lastPage = TotalPagesCaculator(recordsPerPage, totalRecords);

        if (lastPage < pagesPerView)
        {
            startIndex = 1;
            lastIndex = lastPage;
        }
        else if (currentPage > lastPage - pagesPerView)
        {
            startIndex = lastPage - pagesPerView;
            lastIndex = lastPage;
        }
        else if (currentPage >= pagesPerView)
        {
            startIndex = currentPage - 4;
            lastIndex = currentPage + 5;
        }
        addNumbersToList(startIndex, lastIndex, pagingNumbers);

        return pagingNumbers;
    }

    private static void addNumbersToList(int startIndex, int lastIndex, List<Integer> pagingNumbers)
    {
        for (int i = startIndex; i <= lastIndex; i++)
        {
            if (i > 0)
            {
                pagingNumbers.add(new Integer(i));
            }
        }
    }

    public static int TotalPagesCaculator(int recordsPerPage, long totalRecords)
    {
        int total = 0;
        if (totalRecords % recordsPerPage == 0)
        {
            total = (int) (totalRecords / recordsPerPage);
        }
        else
        {
            total = (int) (totalRecords / recordsPerPage + 1);
        }

        return total;
    }
}
