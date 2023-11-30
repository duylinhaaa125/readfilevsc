package Demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
	public static void main(String[] args) {
		try {
			FileReader file = new FileReader(args[0]);
			BufferedReader br = new BufferedReader(file);
			String line;
			int count = 0;
			List<FolderModel> listFolderModel = new ArrayList<FolderModel>();
			while ((line = br.readLine()) != null) {
				count = count + 1;
				if (count != 1) {
					FolderModel folderModel = convertCSVLineToModel(line);
					listFolderModel.add(folderModel);
				}
				
			}
			for (FolderModel folderModel : listFolderModel) {
				List<FolderModel> childrent = listFolderModel.stream().filter(folder -> folderModel.getId() == folder.getParentId()).collect(Collectors.toList());
				folderModel.setChildrent(childrent);
			}
			
			
			exportFolderStructure(listFolderModel);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printFolderName(FolderModel folder, String space) {
		System.out.println(space + folder.getName());
		
		List<FolderModel> childrent = folder.getChildrent();
		childrent = childrent.stream()
				.sorted((c1, c2) -> c1.getName().compareTo(c2.getName()))
				.collect(Collectors.toList());
		if (!childrent.isEmpty()) {
			for (FolderModel child : childrent) {
				printFolderName(child, " " + space);

			}
		}
	}
	
	public static void exportFolderStructure(List<FolderModel> folderModelList) {
		FolderModel root = folderModelList.stream()
				.filter(folderModel -> folderModel.getParentId() == -999999)
				.findFirst().get();
		printFolderName(root, " ");

	} 

	public static FolderModel convertCSVLineToModel(String line) {
		FolderModel result = new FolderModel();
		
		if (line != null) {
			String[] splitData = line.split(",");
			if (splitData.length == 3) {
				result.setId(Integer.parseInt(splitData[0]));
				result.setName(splitData[1]);
				result.setParentId(Integer.parseInt(splitData[2]));
			}else if(splitData.length == 2) {
				result.setId(Integer.parseInt(splitData[0]));
				result.setName(splitData[1]);
				result.setParentId(-999999);
			}
			
		}
		
		return result;
	}
}
