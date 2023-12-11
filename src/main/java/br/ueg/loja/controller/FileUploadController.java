package br.ueg.loja.controller;

import br.ueg.loja.dto.ComputadorDTO;
import br.ueg.loja.storage.StorageFileNotFoundException;
import br.ueg.loja.storage.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "${app.api.base}/arquivo")
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@Operation(description = "Listagem Geral de arquivos" , responses = {
			@ApiResponse(responseCode = "200", description = "Listagem geral",
					content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
							array = @ArraySchema(schema = @Schema(implementation = MultipartFile.class))))})
	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));

		return "uploadForm";
	}

	@Operation(description = "Busca um arquivo especifico" , responses = {
			@ApiResponse(responseCode = "200", description = "Busca arquivo",
					content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
							array = @ArraySchema(schema = @Schema(implementation = MultipartFile.class))))})
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@Operation(description = "Adiciona um arquivo no storage" , responses = {
			@ApiResponse(responseCode = "200", description = "Adiciona arquivo",
					content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
							array = @ArraySchema(schema = @Schema(implementation = MultipartFile.class))))})
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/")
	public ResponseEntity<Resource> handleFileUpload(@RequestPart("file") MultipartFile file) {

		storageService.store(file);

		return ResponseEntity.ok().body(file.getResource());
	}

	@Operation(description = "Adiciona um arquivo renomeado no storage" , responses = {
			@ApiResponse(responseCode = "200", description = "Adiciona arquivo",
					content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
							array = @ArraySchema(schema = @Schema(implementation = MultipartFile.class))))})
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/{filename:.+}")
	public ResponseEntity<Resource> handleFileRenameUpload(@RequestPart("file") MultipartFile file, @PathVariable String filename) {

		storageService.store(file,filename);

		return ResponseEntity.ok().body(file.getResource());
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
