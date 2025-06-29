package com.matiassilva.equipos_api.service;

import com.matiassilva.equipos_api.entity.Equipo;
import com.matiassilva.equipos_api.exception.NotFoundException;
import com.matiassilva.equipos_api.repository.EquipoRepository;
import com.matiassilva.equipos_api.util.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipoServiceImplTest {

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoServiceImpl equipoService;

    private Equipo equipo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        equipo = new Equipo(1L, "River", "Primera División", "Argentina");
    }

    @Test
    void buscarPorId_Existente() {
        when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));

        Equipo resultado = equipoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("River", resultado.getNombre());
        verify(equipoRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_NoExistente() {
        when(equipoRepository.findById(2L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> {
            equipoService.buscarPorId(2L);
        });

        assertEquals("Equipo no encontrado", ex.getMessage());
        verify(equipoRepository, times(1)).findById(2L);
    }

    @Test
    void buscarPorNombre_Encontrado() {
        List<Equipo> lista = List.of(equipo);
        when(equipoRepository.findByNombreContainingIgnoreCase("river")).thenReturn(lista);

        List<Equipo> resultado = equipoService.buscarPorNombre("river");

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("River", resultado.get(0).getNombre());
        verify(equipoRepository, times(1)).findByNombreContainingIgnoreCase("river");
    }

    @Test
    void buscarPorNombre_NoEncontrado() {
        String nombre = "boca";
        when(equipoRepository.findByNombreContainingIgnoreCase(nombre))
                .thenReturn(Collections.emptyList());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> {
            equipoService.buscarPorNombre(nombre);
        });

        assertEquals(ErrorMessage.NO_TEAMS_FOUND, ex.getMessage());
        verify(equipoRepository, times(1)).findByNombreContainingIgnoreCase(nombre);
    }


    @Test
    void crear_Equipo() {
        when(equipoRepository.save(any(Equipo.class))).thenReturn(equipo);

        Equipo creado = equipoService.crear(equipo);

        assertNotNull(creado);
        assertEquals("River", creado.getNombre());
        verify(equipoRepository, times(1)).save(equipo);
    }
}