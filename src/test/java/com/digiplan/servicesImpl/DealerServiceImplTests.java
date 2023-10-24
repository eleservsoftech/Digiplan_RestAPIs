package com.digiplan.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.digiplan.entities.Dealer;
import com.digiplan.repositories.DealerRepository;

@SpringBootTest
public class DealerServiceImplTests {

    @InjectMocks
    private DealerServiceImpl dealerServiceImpl;

    @Mock
    private DealerRepository dealerRepository;

    @Test
    public void test_getDealer() {
        Dealer dealer = new Dealer(1, "H-63", "New Delhi", "Dr. Rahul Khanna", "Dr RAHUL'S DENTAL CARE", "9910138844",
                "rahuldentist@gmail.com", "77.13451699999996", "28.690085", "Orthodonotist", "3rd floor", "near M2K",
                "New Delhi", "Delhi", "110034");
        Optional<Dealer> retrievedData = Optional.of(new Dealer(1, "H-63", "UP", "Dr. Karan Khanna",
                "Dr RAHUL'S DENTAL CARE", "9910135564", "karan@gmail.com", "77.13451699999996", "28.690085",
                "Orthodonotist", "3rd floor", "near M2K", "New Delhi", "Delhi", "110065"));
        Integer id = 1;
        when(dealerRepository.findById(id)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(dealerRepository.getById(id)).thenReturn(dealer);
        assertEquals(id, dealerServiceImpl.getDealer(id).getId());
    }

    @Test
    public void test_getAllCities() {
        List<Dealer> dealer = new ArrayList<>();
        dealer.add(new Dealer(1, "H-63", "New Delhi", "Dr. Rahul Khanna", "Dr RAHUL'S DENTAL CARE", "9910138844",
                "rahuldentist@gmail.com", "77.13451699999996", "28.690085", "Orthodonotist", "3rd floor", "near M2K",
                "New Delhi", "Delhi", "110034"));
        dealer.add(new Dealer(2, "H-63", "New Delhi", "Dr. Rahul Khanna", "Dr RAHUL'S DENTAL CARE", "9910138844",
                "rahuldentist@gmail.com", "77.13451699999996", "28.690085", "Orthodonotist", "3rd floor", "near M2K",
                "New Delhi", "Delhi", "110034"));
        dealer.add(new Dealer(3, "H-63", "New Delhi", "Dr. Rahul Khanna", "Dr RAHUL'S DENTAL CARE", "9910138844",
                "rahuldentist@gmail.com", "77.13451699999996", "28.690085", "Orthodonotist", "3rd floor", "near M2K",
                "New Delhi", "Delhi", "110034"));
        when(dealerRepository.findAll()).thenReturn(dealer);
        assertEquals(3, dealerServiceImpl.getAllDealers().size());
    }

    @Test
    public void test_addDealer() {
        Dealer dealer = new Dealer(1, "H-63", "New Delhi", "Dr. Rahul Khanna", "Dr RAHUL'S DENTAL CARE", "9910138844",
                "rahuldentist@gmail.com", "77.13451699999996", "28.690085", "Orthodonotist", "3rd floor", "near M2K",
                "New Delhi", "Delhi", "110034");
        when(dealerRepository.saveAndFlush(dealer)).thenReturn(dealer);
        assertEquals(dealer, dealerServiceImpl.addDealer(dealer));
    }

    @Test
    public void test_updateDealer() {
        Dealer dealer = new Dealer(1, "H-63", "New Delhi", "Dr. Rahul Khanna", "Dr RAHUL'S DENTAL CARE", "9910138844",
                "rahuldentist@gmail.com", "77.13451699999996", "28.690085", "Orthodonotist", "3rd floor", "near M2K",
                "New Delhi", "Delhi", "110034");
        Optional<Dealer> retrievedData = Optional.of(new Dealer(1, "H-63", "UP", "Dr. Karan Khanna",
                "Dr RAHUL'S DENTAL CARE", "9910135564", "karan@gmail.com", "77.13451699999996", "28.690085",
                "Orthodonotist", "3rd floor", "near M2K", "New Delhi", "Delhi", "110065"));
        Integer id = 1;
        when(dealerRepository.findById(id)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(dealerRepository.saveAndFlush(dealer)).thenReturn(dealer);
        assertEquals(dealer, dealerServiceImpl.updateDealer(id, dealer));
    }

    @Test
    public void test_deleteCity() {
        Integer id = 1;
        dealerServiceImpl.deleteDealer(id);
        verify(dealerRepository, times(1)).deleteById(id);
    }

}
