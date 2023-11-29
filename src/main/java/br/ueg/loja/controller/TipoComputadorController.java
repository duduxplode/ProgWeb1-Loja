package br.ueg.loja.controller;

import br.ueg.loja.dto.ComputadorDTO;
import br.ueg.loja.dto.TipoComputadorDTO;
import br.ueg.loja.mapper.ComputadorMapper;
import br.ueg.loja.mapper.TipoComputadorMapper;
import br.ueg.loja.model.Computador;
import br.ueg.loja.model.TipoComputador;
import br.ueg.loja.service.ComputadorService;
import br.ueg.loja.service.TipoComputadorService;
import br.ueg.loja.service.VendaService;
import br.ueg.loja.storage.StorageService;
import br.ueg.loja.utils.Base64Converter;
import br.ueg.prog.webi.api.controller.CrudController;
import br.ueg.prog.webi.api.controller.CrudEntityIdHashController;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}/tipo_computador")
@PreAuthorize(value = "isAuthenticated()")
public class TipoComputadorController extends CrudEntityIdHashController
        <TipoComputador, TipoComputadorDTO, Long, TipoComputadorMapper, TipoComputadorService> {

}
