package br.ueg.loja.controller;

import br.ueg.loja.dto.TipoComputadorDTO;
import br.ueg.loja.dto.VendaDTO;
import br.ueg.loja.mapper.TipoComputadorMapper;
import br.ueg.loja.mapper.VendaMapper;
import br.ueg.loja.model.TipoComputador;
import br.ueg.loja.model.Venda;
import br.ueg.loja.service.TipoComputadorService;
import br.ueg.loja.service.VendaService;
import br.ueg.prog.webi.api.controller.CrudController;
import br.ueg.prog.webi.api.exception.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}/venda")
public class VendaController extends CrudController
        <Venda, VendaDTO, Long, VendaMapper, VendaService> {
}
