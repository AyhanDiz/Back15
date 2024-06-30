package htwberlin.webtech;

import htwberlin.webtech.web.Beitrag;
import htwberlin.webtech.web.BeitragRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WebtechprojektApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeitragRepository beitragRepository;

    // BeitragController Tests

    @Test
    void addFavorite_shouldReturnSuccessMessage() throws Exception {
        Beitrag beitrag = new Beitrag("Test Title", "2024-06-30", "test.jpg");

        mockMvc.perform(post("/api/beiträge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Title\",\"date\":\"2024-06-30\",\"imgSrc\":\"test.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Beitrag erfolgreich hinzugefügt"));
    }

    @Test
    void getFavorites_shouldReturnListOfBeitrags() throws Exception {
        Beitrag beitrag1 = new Beitrag("Test Title 1", "2024-06-30", "test1.jpg");
        Beitrag beitrag2 = new Beitrag("Test Title 2", "2024-06-30", "test2.jpg");

        when(beitragRepository.findAll()).thenReturn(Arrays.asList(beitrag1, beitrag2));

        mockMvc.perform(get("/api/beiträge"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"title\":\"Test Title 1\",\"date\":\"2024-06-30\",\"imgSrc\":\"test1.jpg\"},{\"title\":\"Test Title 2\",\"date\":\"2024-06-30\",\"imgSrc\":\"test2.jpg\"}]"));
    }

    @Test
    void getFavoriteById_shouldReturnBeitrag() throws Exception {
        Beitrag beitrag = new Beitrag("Test Title", "2024-06-30", "test.jpg");

        when(beitragRepository.findById(1L)).thenReturn(Optional.of(beitrag));

        mockMvc.perform(get("/api/beiträge/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"Test Title\",\"date\":\"2024-06-30\",\"imgSrc\":\"test.jpg\"}"));
    }

    @Test
    void updateFavorite_shouldReturnUpdatedBeitrag() throws Exception {
        Beitrag beitrag = new Beitrag("Original Title", "2024-06-30", "original.jpg");
        when(beitragRepository.findById(1L)).thenReturn(Optional.of(beitrag));

        mockMvc.perform(put("/api/beiträge/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"date\":\"2024-06-30\",\"imgSrc\":\"updated.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"Updated Title\",\"date\":\"2024-06-30\",\"imgSrc\":\"updated.jpg\"}"));
    }

    @Test
    void deleteFavorite_shouldReturnSuccessMessage() throws Exception {
        Beitrag beitrag = new Beitrag("Test Title", "2024-06-30", "test.jpg");
        when(beitragRepository.findById(1L)).thenReturn(Optional.of(beitrag));

        mockMvc.perform(delete("/api/beiträge/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Beitrag erfolgreich gelöscht"));
    }

    // HelloWorldController Tests

    @Test
    void showHelloWorldPage_shouldReturnHelloworldView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("helloworld"));
    }

    // Context Load Test

    @Test
    void contextLoads() {
        // Test that the context successfully loads
    }
}
