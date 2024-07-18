package dev.mvc.shoes;

import dev.mvc.basket.BasketVO;
import dev.mvc.category.CategoryListVO;
import dev.mvc.category.CategoryVO;
import dev.mvc.likes.LikesVO;
import dev.mvc.member.MemberVO;
import dev.mvc.option.OptionVO;
import dev.mvc.review.ReviewVO;
import dev.mvc.shoesFile.ShoesFileVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ShoesAllVO {
  private ShoesVO shoesVO;
  private CategoryVO categoryVO;
  private CategoryListVO categoryListVO;
  private OptionVO optionVO;
  private ReviewVO reviewVO;
  private LikesVO likesVO;
  private MemberVO memberVO;
  private BasketVO basketVO;
  private ShoesFileVO shoesFileVO;
}
