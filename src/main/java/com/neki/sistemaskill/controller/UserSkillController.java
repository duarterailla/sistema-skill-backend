package com.neki.sistemaskill.controller;

import com.neki.sistemaskill.model.UserSkill;
import com.neki.sistemaskill.model.User;
import com.neki.sistemaskill.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RequestMapping("/api/user-skills")
public class UserSkillController {

    @GetMapping("/{userId}/pdf")
    public void exportUserSkillsPdf(@PathVariable Integer userId, HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=skills_usuario_" + userId + ".pdf");

        List<UserSkill> userSkills = userSkillService.listUserSkills(userId);
        User user = null;
        if (!userSkills.isEmpty()) {
            user = userSkills.get(0).getUser();
        }
        // Se não houver skills, buscar usuário pelo id
        if (user == null) {
            user = userSkillService.getUserById(userId);
        }

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHora = java.time.LocalDateTime.now().format(formatter);

        document.add(new Paragraph("Relatório de Skills do Usuário"));
        document.add(new Paragraph(" " ));
        if (user != null) {
            document.add(new Paragraph("Nome: " + user.getNickname()));
            document.add(new Paragraph("E-mail: " + user.getEmail()));
        }
        document.add(new Paragraph("Data/Hora do download: " + dataHora));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.addCell("Skill");
        table.addCell("Nível");
        table.addCell("Descrição");
        table.addCell("Usuário");

        for (UserSkill us : userSkills) {
            table.addCell(us.getSkill().getNome());
            table.addCell(String.valueOf(us.getLevel()));
            table.addCell(us.getSkill().getDescricao());
            table.addCell(us.getUser().getNickname());
        }

        document.add(table);
        document.close();
    }
    @Autowired
    private UserSkillService userSkillService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserSkill>> listUserSkills(@PathVariable Integer userId) {
        return ResponseEntity.ok(userSkillService.listUserSkills(userId));
    }

    @PostMapping
    public ResponseEntity<UserSkill> associateSkill(@RequestBody Map<String, Object> body) {
    Object userIdObj = body.get("userId");
    Object skillIdObj = body.get("skillId");
    Integer userId = (userIdObj instanceof Integer) ? (Integer) userIdObj : Integer.parseInt(userIdObj.toString());
    Integer skillId = (skillIdObj instanceof Integer) ? (Integer) skillIdObj : Integer.parseInt(skillIdObj.toString());
    Object levelObj = body.get("level");
    String level = (levelObj != null) ? levelObj.toString() : null;
    Object descricaoObj = body.get("descricao");
    String descricao = (descricaoObj != null) ? descricaoObj.toString() : null;
    return ResponseEntity.ok(userSkillService.associateSkill(userId, skillId, level, descricao));
    }

    @PutMapping("/{userSkillId}")
    public ResponseEntity<UserSkill> updateUserSkill(@PathVariable Integer userSkillId, @RequestBody Map<String, Object> body) {
        Object levelObj = body.get("level");
        String level = (levelObj != null) ? levelObj.toString() : null;
        return ResponseEntity.ok(userSkillService.updateUserSkill(userSkillId, level));
    }

    @DeleteMapping("/{userSkillId}")
    public ResponseEntity<?> deleteUserSkill(@PathVariable Integer userSkillId) {
        userSkillService.deleteUserSkill(userSkillId);
        return ResponseEntity.ok(Map.of("message", "Associação removida com sucesso"));
    }
}
