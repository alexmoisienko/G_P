package Home.G_P;

import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FileWorker {

    public void Write(User user) throws Exception {
        try (
                FileWriter writer = new FileWriter("users_from_db.txt", true)
        ) {
            writer.write(user.toString());
            writer.write("\n");
            writer.flush();
        } catch(Exception e) {
            throw new Exception(e);
        }
    }

    public String Read() throws Exception {
        try (
                BufferedReader reader = new BufferedReader(new FileReader("ten_lines.txt"))
        ) {
            List<String> lines = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            Random random = new Random();
            int index = random.nextInt(lines.size());
            return lines.get(index);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
